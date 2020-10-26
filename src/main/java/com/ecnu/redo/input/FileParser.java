package com.ecnu.redo.input;

import java.io.File;

public interface FileParser {


    CostMatrix parseFile(File file) throws Exception;

}
