package com.ecnu.refactoring.input;

import java.io.File;

public interface FileParser {


    RefactorMatrix parseFile(File file) throws Exception;

}
