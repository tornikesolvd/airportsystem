package com.solvd.airportsystem.pattern.factory;

import com.solvd.airportsystem.domain.Airport;

import java.io.File;

public interface Parser {
    Airport parse(File file);
}

