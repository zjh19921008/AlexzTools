package security;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.KeyPair;

public class ExportPrivateKey {
    public static void main(String[] args) throws Exception {
        JSch jsch = new JSch();

        // 生成新的SSH密钥对
        KeyPair keyPair = KeyPair.genKeyPair(jsch, KeyPair.RSA);
        keyPair.writePrivateKey("private_key.ppk"); // 这将保存为OpenSSH私钥格式
    }
}
