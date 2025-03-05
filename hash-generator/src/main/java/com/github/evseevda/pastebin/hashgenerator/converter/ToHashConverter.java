package com.github.evseevda.pastebin.hashgenerator.converter;

public interface ToHashConverter<S> {

    String convert(S seed);

}
