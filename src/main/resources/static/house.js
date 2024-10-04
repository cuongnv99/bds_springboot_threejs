import * as THREE from 'three';
import { OrbitControls } from 'OrbitControls';
import { RoomEnvironment } from 'RoomEnvironment';
import { GLTFLoader } from 'GLTFLoader';
import { DRACOLoader } from 'DRACOLoader';
import Stats from 'https://cdnjs.cloudflare.com/ajax/libs/stats.js/r17/Stats.min.js';

let mixer;

const clock = new THREE.Clock();
const container = document.getElementById('container');

const stats = new Stats();
container.appendChild(stats.dom);

const renderer = new THREE.WebGLRenderer({ antialias: true });
renderer.setPixelRatio(window.devicePixelRatio);
renderer.setSize(window.innerWidth, window.innerHeight);
container.appendChild(renderer.domElement);

const pmremGenerator = new THREE.PMREMGenerator(renderer);

const scene = new THREE.Scene();
scene.background = new THREE.Color(0xbfe3dd);
scene.environment = pmremGenerator.fromScene(new RoomEnvironment(), 0.04).texture;

const camera = new THREE.PerspectiveCamera(40, window.innerWidth / window.innerHeight, 1, 100);
camera.position.set(5, 2, 8);

const controls = new OrbitControls(camera, renderer.domElement);
controls.target.set(0, 0.5, 0);
controls.update();
controls.enablePan = false;
controls.enableDamping = true;

const dracoLoader = new DRACOLoader();
dracoLoader.setDecoderPath('https://cdn.jsdelivr.net/npm/three@0.130.1/examples/js/libs/draco/gltf/');

const loader = new GLTFLoader();
loader.setDRACOLoader(dracoLoader);
loader.load('https://threejs.org/examples/models/gltf/LittlestTokyo.glb', function (gltf) {
    const model = gltf.scene;
    model.position.set(1, 1, 0);
    model.scale.set(0.01, 0.01, 0.01);
    scene.add(model);

    mixer = new THREE.AnimationMixer(model);
    mixer.clipAction(gltf.animations[0]).play();

    renderer.setAnimationLoop(animate);

}, undefined, function (e) {
    console.error(e);
});

window.onresize = function () {
    camera.aspect = window.innerWidth / window.innerHeight;
    camera.updateProjectionMatrix();
    renderer.setSize(window.innerWidth, window.innerHeight);
};

function animate() {
    const delta = clock.getDelta();
    mixer.update(delta);
    controls.update();
    stats.update();
    renderer.render(scene, camera);
}
