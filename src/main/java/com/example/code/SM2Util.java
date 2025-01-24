package com.example.code;

import org.bouncycastle.asn1.*;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.ec.CustomNamedCurves;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.*;
import org.bouncycastle.crypto.signers.SM2Signer;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.spec.ECParameterSpec;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.math.ec.custom.gm.SM2P256V1Curve;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Enumeration;

/**
 * @author: KeXue
 * @time: 2022/8/25
 * @description: SM2加解密工具类
 */
public class SM2Util {
    private static final Logger log = LoggerFactory.getLogger(SM2Util.class);

    private SM2Util() {
        throw new IllegalStateException("Utility class");
    }

    private static final String STD_NAME = "sm2p256v1";

    public static final SM2P256V1Curve CURVE = new SM2P256V1Curve();

    public static final BigInteger SM2_ECC_P = CURVE.getQ();

    public static final BigInteger SM2_ECC_A = CURVE.getA().toBigInteger();

    public static final BigInteger SM2_ECC_B = CURVE.getB().toBigInteger();

    public static final BigInteger SM2_ECC_N = CURVE.getOrder();

    public static final BigInteger SM2_ECC_H = CURVE.getCofactor();

    public static final BigInteger SM2_ECC_GX =
        new BigInteger("32C4AE2C1F1981195F9904466A39C9948FE30BBFF2660BE1715A4589334C74C7", 16);

    public static final BigInteger SM2_ECC_GY =
        new BigInteger("BC3736A2F4F6779C59BDCEE36B692153D0A9877CC62A474002DF32E52139F0A0", 16);

    public static final ECPoint G_POINT = CURVE.createPoint(SM2_ECC_GX, SM2_ECC_GY);

    public static final ECDomainParameters DOMAIN_PARAMS = new ECDomainParameters(
        // 曲线参数，此处以SM2的默认曲线参数为例
        CustomNamedCurves.getByName(STD_NAME));

    public static final AsymmetricCipherKeyPair KEY_PAIR = getCipherKeyPair();

    /**
     * SM2加密算法
     *
     * @param publicKey 公钥
     * @param data 明文数据
     * @return 秘文
     */
    public static byte[] encrypt(String publicKey, byte[] data) {
        ECPublicKeyParameters ecPublicKeyParameters = encodePublicKey(Hex.decode(publicKey));
        SM2Engine engine = new SM2Engine();
        engine.init(true, new ParametersWithRandom(ecPublicKeyParameters, new SecureRandom()));

        byte[] bytes = null;
        try {
            byte[] cipherText = engine.processBlock(data, 0, data.length);
            bytes = C1C2C3ToC1C3C2(cipherText);
        } catch (Exception e) {
            log.warn("SM2加密时出现异常:" + e.getMessage());
        }
        return bytes;
    }

    /**
     * SM2解密算法
     *
     * @param privateKey 私钥
     * @param cipherData 密文数据
     * @return
     */
    public static byte[] decrypt(String privateKey, byte[] cipherData) {
        ECPrivateKeyParameters ecPrivateKeyParameters = encodePrivateKey(Hex.decode(privateKey));
        SM2Engine engine = new SM2Engine();
        engine.init(false, ecPrivateKeyParameters);

        byte[] bytes = null;
        try {
            cipherData = C1C3C2ToC1C2C3(cipherData);
            bytes = engine.processBlock(cipherData, 0, cipherData.length);
        } catch (Exception e) {
            log.warn("SM2解密时出现异常:" + e.getMessage());
        }
        return bytes;
    }

    /**
     * 签名算法
     *
     * @param privateKey 私钥
     * @param data 明文数据
     * @return
     */
    public static byte[] sign(String privateKey, byte[] data) {
        ECPrivateKeyParameters ecPrivateKeyParameters = encodePrivateKey(hexToByte(privateKey));
        SM2Signer signer = new SM2Signer();
        ParametersWithID parameters = new ParametersWithID(ecPrivateKeyParameters, "1234567812345678".getBytes());
        signer.init(true, parameters);
        signer.update(data, 0, data.length);

        byte[] signature = null;
        try {
            signature = decodeDERSignature(signer.generateSignature());
        } catch (Exception e) {
            log.warn("SM2签名时出现异常:" + e.getMessage());
        }
        return signature;
    }

    private static byte[] hexToByte(String hex) throws IllegalArgumentException {
        if (hex.length() % 2 != 0) {
            throw new IllegalArgumentException();
        }
        char[] arr = hex.toCharArray();
        byte[] b = new byte[hex.length() / 2];
        for (int i = 0, j = 0, l = hex.length(); i < l; i++, j++) {
            String swap = "" + arr[i++] + arr[i];
            int byteInt = Integer.parseInt(swap, 16) & 0xFF;
            b[j] = BigInteger.valueOf(byteInt).byteValue();
        }
        return b;
    }

    private static byte[] C1C2C3ToC1C3C2(byte[] cipherText) throws Exception {
        if (cipherText != null && cipherText.length >= 97) {
            byte[] bytes = new byte[cipherText.length];
            System.arraycopy(cipherText, 0, bytes, 0, 65);
            System.arraycopy(cipherText, cipherText.length - 32, bytes, 65, 32);
            System.arraycopy(cipherText, 65, bytes, 97, cipherText.length - 97);
            return bytes;
        } else {
            throw new Exception("SM2 cipher text error, must be more than 96 bytes and in the format C1||C3||C2.");
        }
    }

    private static byte[] C1C3C2ToC1C2C3(byte[] cipherText) throws Exception {
        if (cipherText != null && cipherText.length >= 97) {
            byte[] bytes = new byte[cipherText.length];
            System.arraycopy(cipherText, 0, bytes, 0, 65);
            System.arraycopy(cipherText, 97, bytes, 65, cipherText.length - 97);
            System.arraycopy(cipherText, 65, bytes, cipherText.length - 32, 32);
            return bytes;
        } else {
            throw new Exception("SM2 cipher text error, must be more than 96 bytes and in the format C1||C3||C2.");
        }
    }

    private static ECPublicKeyParameters encodePublicKey(byte[] value) {
        byte[] x = new byte[32];
        byte[] y = new byte[32];
        System.arraycopy(value, 1, x, 0, 32);
        System.arraycopy(value, 33, y, 0, 32);
        BigInteger X = new BigInteger(1, x);
        BigInteger Y = new BigInteger(1, y);
        ECPoint Q = getSM2Curve().createPoint(X, Y);
        return new ECPublicKeyParameters(Q, getECDomainParameters());
    }

    private static ECCurve getSM2Curve() {
        ECParameterSpec spec = ECNamedCurveTable.getParameterSpec(STD_NAME);
        return spec.getCurve();
    }

    private static ECPrivateKeyParameters encodePrivateKey(byte[] value) {
        BigInteger d = new BigInteger(1, value);
        return new ECPrivateKeyParameters(d, getECDomainParameters());
    }

    private static ECDomainParameters getECDomainParameters() {
        ECParameterSpec spec = ECNamedCurveTable.getParameterSpec(STD_NAME);
        return new ECDomainParameters(spec.getCurve(), spec.getG(), spec.getN(), spec.getH(), spec.getSeed());
    }

    private static byte[] decodeDERSignature(byte[] signature) {
        ASN1InputStream stream = new ASN1InputStream(new ByteArrayInputStream(signature));

        byte[] bytes = new byte[64];
        try {
            ASN1Sequence primitive = (ASN1Sequence) stream.readObject();
            Enumeration enumeration = primitive.getObjects();
            BigInteger R = ((ASN1Integer) enumeration.nextElement()).getValue();
            BigInteger S = ((ASN1Integer) enumeration.nextElement()).getValue();
            byte[] r = format(R.toByteArray());
            byte[] s = format(S.toByteArray());
            System.arraycopy(r, 0, bytes, 0, 32);
            System.arraycopy(s, 0, bytes, 32, 32);
        } catch (Exception e) {
            log.warn("decodeDERSignature时出现异常:" + e.getMessage());
        }
        return bytes;
    }

    public static byte[] encodeDERSignature(byte[] signature) {
        byte[] r = new byte[32];
        byte[] s = new byte[32];
        System.arraycopy(signature, 0, r, 0, 32);
        System.arraycopy(signature, 32, s, 0, 32);
        ASN1EncodableVector vector = new ASN1EncodableVector();
        vector.add(new ASN1Integer(new BigInteger(1, r)));
        vector.add(new ASN1Integer(new BigInteger(1, s)));

        byte[] encoded = null;
        try {
            encoded = (new DERSequence(vector)).getEncoded();
        } catch (Exception e) {
            log.warn("encodeDERSignature时出现异常:" + e.getMessage());
        }
        return encoded;
    }

    private static byte[] format(byte[] value) {
        if (value.length == 32) {
            return value;
        } else {
            byte[] bytes = new byte[32];
            if (value.length > 32) {
                System.arraycopy(value, value.length - 32, bytes, 0, 32);
            } else {
                System.arraycopy(value, 0, bytes, 32 - value.length, value.length);
            }
            return bytes;
        }
    }

    /**
     * 获取私钥
     *
     * @return 私钥字符串
     */
    public static String getPrivateKey() {
        ECPrivateKeyParameters privateKey = (ECPrivateKeyParameters) KEY_PAIR.getPrivate();
        byte[] privateKeyBytes = privateKey.getD().toByteArray();

        return bytesToHex(privateKeyBytes);
    }

    /**
     * 获取公钥
     *
     * @return 公钥字符串
     */
    public static String getPublicKey() {
        ECPublicKeyParameters publicKey = (ECPublicKeyParameters) KEY_PAIR.getPublic();
        byte[] publicKeyBytes = publicKey.getQ().getEncoded(false);
        return bytesToHex(publicKeyBytes);
    }

    private static AsymmetricCipherKeyPair getCipherKeyPair() {
        ECKeyPairGenerator generator = new ECKeyPairGenerator();
        ECKeyGenerationParameters keyGenParams =
            new ECKeyGenerationParameters(SM2Util.DOMAIN_PARAMS, new SecureRandom());
        generator.init(keyGenParams);
        return generator.generateKeyPair();
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02X", b));
        }
        return sb.toString();
    }
}
