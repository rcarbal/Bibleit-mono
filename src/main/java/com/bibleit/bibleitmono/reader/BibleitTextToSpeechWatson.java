package com.bibleit.bibleitmono.reader;

import com.bibleit.bibleitmono.reader.voice.BibleitVoice;
import com.ibm.cloud.sdk.core.http.HttpConfigOptions;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.text_to_speech.v1.model.Voices;

import java.util.Map;

public class BibleitTextToSpeechWatson implements BibleitTextToSpeech {

    private Map<String, String> env = System.getenv();

    @Override
    public BibleitVoice getVoices() {
        Authenticator authenticator = new IamAuthenticator(env.get("WATSON_API_KEY"));
        TextToSpeech service = new TextToSpeech(authenticator);

        Voices voices = service.listVoices().execute().getResult();
        System.out.println(voices);
        return null;
    }

    @Override
    public void runSample(){

    }
//    WATSON_URL
//    WATSON_API_KEY
    @Override
    public Object getTextToSpeechObject() {
        IamAuthenticator authenticator = getSDKManagedTokenAuthentication();
        TextToSpeech textToSpeechObject = new TextToSpeech(authenticator);
        textToSpeechObject.setServiceUrl(env.get("WATSON_URL"));

        return textToSpeechObject;
    }

    public IamAuthenticator getSDKManagedTokenAuthentication(){

        return new IamAuthenticator(env.get("WATSON_API_KEY"));
    }

    public TextToSpeech disableSSLVerification(TextToSpeech textToSpeech){

        HttpConfigOptions configOptions = new HttpConfigOptions.Builder()
                .disableSslVerification(true)
                .build();

        textToSpeech.configureClient(configOptions);

        return textToSpeech;
    }
}