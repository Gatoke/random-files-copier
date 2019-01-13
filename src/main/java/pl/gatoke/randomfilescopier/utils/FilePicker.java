package pl.gatoke.randomfilescopier.utils;

import java.io.File;
import java.util.Random;

public class FilePicker {

    private static Random random = new Random();

    public static File getFile(final File file) {
        if (file.isFile()) {
            return file;
        }

        final File[] filesInside = file.listFiles();
        if (filesInside == null) {
            return null;
        }

        final File fileInside = filesInside[random.nextInt(filesInside.length)];
        return getFile(fileInside);
    }
}
