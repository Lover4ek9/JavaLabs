package org.example;

import java.util.ArrayList;
import java.util.List;

public class Directory {
    private final String name;
    private final List<FileInfo> files = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void addFile(String name, long size) {
        files.add(new FileInfo(name, size));
    }

    public List<FileInfo> getFiles() {
        return List.copyOf(files);
    }

    public class FileInfo {
        private final String name;
        private final long size;

        public FileInfo(String name, long size) {
            this.name = name;
            this.size = size;
        }

        public String getName() {
            return name;
        }

        public long getSize() {
            return size;
        }

        @Override
        public String toString() {
            return Directory.this.name + "/" + name + " — " + size + " байт";
        }
    }
}
