package com.github.evseevda.pastebin.hashgenerator.hash.converter;

import org.springframework.stereotype.Component;

import java.math.BigInteger;

import static org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString;
import static org.apache.commons.codec.binary.Base64.encodeInteger;

@Component
public class IntegerToHashConverter implements ToHashConverter<Integer> {

    @Override
    public String convert(Integer seed) {
        return encodeBase64URLSafeString(encodeInteger(BigInteger.valueOf(seed)));
    }

}
