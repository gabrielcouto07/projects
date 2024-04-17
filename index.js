const image = document.getElementById('cover'),
    title = document.getElementById('music-title'),
    artist = document.getElementById('music-artist'),
    currentTimeEl = document.getElementById('current-time'),
    durationEl = document.getElementById('duration'),
    progress = document.getElementById('progress'),
    playerProgress = document.getElementById('player-progress'),
    prevBtn = document.getElementById('prev'),
    playBtn = document.getElementById('play'),
    nextBtn = document.getElementById('next'),
    background = document.getElementById('bd-img');

const music = new Audio();

const songs =[ 
    {
        path: 'assets/1.mp3',
        displayName: 'The Charmer\'s Call',
        cover: 'assets/1.jpg',
        artist: 'Hanu Dixit'
    },
    {
        path: 'assets/2.mp3',
        displayName: 'You Will Never See Me Coming',
        cover: 'assets/2.jpg',
        artist: 'NEFFEX'
    },
    {
        path: 'assets/3.mp3',
        displayName: 'Intellect',
        cover: 'assets/3.jpg',
        artist: 'Yung Logos'
    }
]; 

let musicIndex = 0;
let isPlaying = false;

function togglePlay(){
    if(isPlaying){
        pauseMusic();
    }else{
        playMusic();
    }
}

function playMusic(){
    isPlaying = true;
    //mudar o ícone do botão de play
    playBtn.classList.replace('fa-play', 'fa-pause');
    //mostrar o nome do botão ao passar o mouse por cima
    playBtn.setAttribute('title', 'pause');
    music.play();
}

function pauseMusic(){
    isPlaying = false;
    //mudar o ícone do botão de pause
    playBtn.classList.replace('fa-pause', 'fa-play');
    //mostrar o nome do botão ao passar o mouse por cima
    playBtn.setAttribute('title', 'play');
    music.pause();
}

function loadMusic(song){
    music.src = song.path; // Corrigido
    title.textContent = song.displayName;
    artist.textContent = song.artist;
    image.src = song.cover;
    background.src = song.cover;
}

function changeMusic(direction){
    musicIndex = (musicIndex + direction + songs.length) % songs.length;
    loadMusic(songs[musicIndex]);
    playMusic();
}

function updateProgressBar(){
    const {currentTime, duration} = music;
    const progressPercent = (currentTime / duration) * 100;
    progress.style.width = `${progressPercent}%`; // Corrigido

    const formatTime = (time) => String(Math.floor(time)).padStart(2, '0');
    durationEl.textContent = `${Math.floor(duration / 60)}:${formatTime(duration % 60)}`; // Modificado
    currentTimeEl.textContent = `${Math.floor(currentTime / 60)}:${formatTime(currentTime % 60)}`; // Modificado
}

function setProgressBar (e) {
    const width = playerProgress.clientWidth; // Corrigido
    const clickX = e.offsetX;
    music.currentTime = (clickX / width) * music.duration;
}

playBtn.addEventListener('click', togglePlay); // Corrigido
prevBtn.addEventListener('click', () => changeMusic(-1));
nextBtn.addEventListener('click', () => changeMusic(1));
music.addEventListener('ended', () => changeMusic(1));
music.addEventListener('timeupdate', updateProgressBar);
playerProgress.addEventListener('mousedown', setProgressBar);

loadMusic(songs[musicIndex]);