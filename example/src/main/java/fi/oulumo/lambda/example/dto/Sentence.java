package fi.oulumo.lambda.example.dto;

/**
 * @author Zsolt Homorodi (zsolt.homorodi@oulumo.com)
 */
public class Sentence {
    private String sentence;

    protected Sentence() {
    }

    public Sentence(String sentence) {
        this.sentence = sentence;
    }

    public String getSentence() {
        return sentence;
    }
}
