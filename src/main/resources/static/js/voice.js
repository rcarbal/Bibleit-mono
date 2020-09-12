let playVoiceVar = document.getElementById("voiceSelected");
let playVoiceVal = playVoiceVar.dataset.voice;
let voiceIcon = document.getElementById("voiceIcon");

const OFF_ICON = "fa fa-volume-off fa-2x";
const ON_ICON = "fa fa-volume-up fa-2x";




function getCookie(){
    return document.cookie;
}

// get child from preview div
$(document).ready(function () {
    $('#voiceSelected').on('click', function (event) {
        changeVoiceIcon();
    });
});

function changeVoiceIcon(){
    
    if (voiceIcon.classList.contains("fa-volume-off")){
        voiceIcon.classList = "";
        voiceIcon.classList = ON_ICON;

        document.cookie = "voice=on"

    }else{
        voiceIcon.classList = "";
        voiceIcon.classList = OFF_ICON;

        document.cookie = "voice=off"
    }
}

function setVoiceCookie(voiceValue){

}

