package io.github.limengning.wechat;

import java.util.Arrays;
import org.apache.commons.codec.digest.DigestUtils;

public class EntryService {
    private final String token;
    private final String encodingAESKey;

    public EntryService(String token, String encodingAESKey) {
        this.token = token;
        this.encodingAESKey = encodingAESKey;
    }

    public boolean checkSignature(String signature, String timestamp, String nonce) {
        return signature.equals(generateSignature(timestamp, nonce));
    }

    private String generateSignature(String timestamp, String nonce) {
        String array[] = { timestamp, nonce, token };
        Arrays.sort(array);
        StringBuilder sb = new StringBuilder();
        for (String str : array) {
            sb.append(str);
        }
        return DigestUtils.sha1Hex(sb.toString();
    }
}
