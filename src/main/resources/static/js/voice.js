let playVoiceVar = document.getElementById("voiceSelected");
let playVoiceVal = playVoiceVar.dataset.voice;
let voiceIcon = document.getElementById("voiceIcon");
let voiceAudio;

const OFF_ICON = "fa fa-volume-off fa-2x";
const ON_ICON = "fa fa-volume-up fa-2x";

let voiceCookie =getCookie("voice");

setVoiceCookie();

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

        // check if questino needs to be read
        let questionSeletedId = getCurrentSelectedQuestionId();
        if (questionSeletedId !== undefined){

          //call voice api
          getVoiceAudio("sample");
        }

    }else{
        voiceIcon.classList = "";
        voiceIcon.classList = OFF_ICON;

        document.cookie = "voice=off";
        
        //TODO fix if statement.
        if (voiceAudio == undefined){
          
        }else {
          voiceAudio.pause();
          voiceAudio.currentTime = 0;
        }
    }
}

function setVoiceCookie(){
    if (voiceCookie === "off"){
        voiceIcon.classList = "";
        voiceIcon.classList = OFF_ICON;

        document.cookie = "voice=off"

    }else{
        voiceIcon.classList = "";
        voiceIcon.classList = ON_ICON;

        document.cookie = "voice=on"
    }
}

function getCookie(cname) {
    var name = cname + "=";
    var decodedCookie = decodeURIComponent(document.cookie);
    var ca = decodedCookie.split(';');
    for(var i = 0; i <ca.length; i++) {
      var c = ca[i];
      while (c.charAt(0) == ' ') {
        c = c.substring(1);
      }
      if (c.indexOf(name) == 0) {
        return c.substring(name.length, c.length);
      }
    }
    return "";
  }

  function getVoiceAudio(audioId){

    axios.get(`/api/voice/getAudio?audioId=${audioId}`)
      .then((response)=> {
        console.log(response);
        voiceAudio = new Audio(response.data);
        voiceAudio.play();
      });
}