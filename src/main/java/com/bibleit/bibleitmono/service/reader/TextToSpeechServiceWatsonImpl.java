package com.bibleit.bibleitmono.service.reader;

import com.bibleit.bibleitmono.service.reader.voice.BibleitVoice;
import com.ibm.cloud.sdk.core.http.HttpConfigOptions;
import com.ibm.cloud.sdk.core.security.Authenticator;
import com.ibm.cloud.sdk.core.security.IamAuthenticator;
import com.ibm.watson.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.text_to_speech.v1.model.Voices;
import com.ibm.watson.text_to_speech.v1.util.WaveUtils;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

@Component
public class TextToSpeechServiceWatsonImpl implements TextToSpeechService {

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
                            .text("<prosody rate=\"-20%\">" +
                                    "Healing a man on the Sabbath day does not break the law of Moses.  " +
                                    "Jesus Christ compared the approval of circumcising on the Sabbath, so to not " +
                                    "break the law of Moses, to the approval of healing a man's entire body on the Sabbath" +
                                    "</prosody>")
                            .accept("audio/wav")
                            .voice("en-US_MichaelV3Voice")
                            .build();

            InputStream inputStream =
                    textToSpeech.synthesize(synthesizeOptions).execute().getResult();
            InputStream in = WaveUtils.reWriteWaveHeader(inputStream);

            OutputStream out = new FileOutputStream("sample.wav");
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

    @Override
    public boolean generateAudioFile(String questionId, String answerText, String voiceRate) {
        IamAuthenticator authenticator = new IamAuthenticator(env.get("WATSON_API_KEY"));
        TextToSpeech textToSpeech = new TextToSpeech(authenticator);
        textToSpeech.setServiceUrl(env.get("WATSON_URL"));

        try {
            SynthesizeOptions synthesizeOptions =
                    new SynthesizeOptions.Builder()
                            .text("<prosody rate=\""+ voiceRate +"\">" +
                                    answerText +
                                    "</prosody>")
                            .accept("audio/wav")
                            .voice("en-US_MichaelV3Voice")
                            .build();

            InputStream inputStream =
                    textToSpeech.synthesize(synthesizeOptions).execute().getResult();
            InputStream in = WaveUtils.reWriteWaveHeader(inputStream);

            OutputStream out = new FileOutputStream("audios/"+questionId + ".wav");
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
