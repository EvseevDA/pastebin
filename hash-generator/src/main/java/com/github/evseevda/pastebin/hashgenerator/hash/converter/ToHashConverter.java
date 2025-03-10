package com.github.evseevda.pastebin.hashgenerator.hash.converter;

public interface ToHashConverter<S> {

    String convert(S seed);

}
