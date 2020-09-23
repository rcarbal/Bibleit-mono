package com.bibleit.bibleitmono.reader;

import com.bibleit.bibleitmono.reader.voice.BibleitVoice;
import com.ibm.cloud.sdk.core.http.HttpConfigOptions;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.text_to_speech.v1.model.Voices;
import com.ibm.watson.text_to_speech.v1.util.WaveUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    public boolean runSample(){
        IamAuthenticator authenticator = new IamAuthenticator(env.get("WATSON_API_KEY"));
        TextToSpeech textToSpeech = new TextToSpeech(authenticator);
        textToSpeech.setServiceUrl(env.get("WATSON_URL"));

        try {
            SynthesizeOptions synthesizeOptions =
                    new SynthesizeOptions.Builder()
                            .text("Jesus calls himself the living bread because he has the power of life in him " +
                                    "and he came from heaven. The Israelite people needed to eat, so God gave them " +
                                    "manna from heaven in the wilderness, but they still died. It is the same with" +
                                    " everyone else, they will all hunger eat and die. But Jesus being the true bread " +
                                    "from heaven with the power of life in him gives eternal life to those who believe " +
                                    "in him so they will not die.")
                            .accept("audio/wav")
                            .voice("en-US_AllisonV3Voice")
                            .build();

            InputStream inputStream =
                    textToSpeech.synthesize(synthesizeOptions).execute().getResult();
            InputStream in = WaveUtils.reWriteWaveHeader(inputStream);

            OutputStream out = new FileOutputStream("hello_world.wav");
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }

            out.close();
            in.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

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
