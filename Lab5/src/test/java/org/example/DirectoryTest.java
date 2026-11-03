package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DirectoryTest {
    @Test
    void createsEmptyDirectoryWithName() {
        Directory directory = new Directory("Документы");
        assertEquals("Документы", directory.getName());
        assertTrue(directory.getFiles().isEmpty());
    }

    @Test
    void addsFilesAndStoresTheirInformation() {
        Directory directory = new Directory("Документы");
        directory.addFile("report.txt", 1200);
        directory.addFile("notes.txt", 500);
        List<Directory.FileInfo> files = directory.getFiles();
        assertEquals(2, files.size());
        assertEquals("report.txt", files.get(0).getName());
        assertEquals(1200L, files.get(0).getSize());
        assertEquals("notes.txt", files.get(1).getName());
        assertEquals(500L, files.get(1).getSize());
    }

    @Test
    void innerFileUsesItsOwnDirectoryName() {
        Directory documents = new Directory("Документы");
        Directory archive = new Directory("Архив");
        Directory.FileInfo first = documents.new FileInfo("report.txt", 1200);
        Directory.FileInfo second = archive.new FileInfo("report.txt", 1200);
        assertEquals("Документы/report.txt — 1200 байт", first.toString());
        assertEquals("Архив/report.txt — 1200 байт", second.toString());
    }

    @Test
    void returnedListCannotBeModified() {
        Directory directory = new Directory("Документы");
        directory.addFile("report.txt", 1200);
        List<Directory.FileInfo> files = directory.getFiles();
        assertThrows(UnsupportedOperationException.class, files::clear);
        assertEquals(1, directory.getFiles().size());
    }

    @Test
    void sortsFilesBySizeUsingAnonymousComparator() {
        Directory directory = new Directory("Документы");
        directory.addFile("large.bin", Long.MAX_VALUE);
        directory.addFile("empty.txt", 0);
        directory.addFile("notes.txt", 500);
        List<Directory.FileInfo> sorted = Main.sortFilesBySize(directory);
        assertEquals(3, sorted.size());
        assertEquals("empty.txt", sorted.get(0).getName());
        assertEquals("notes.txt", sorted.get(1).getName());
        assertEquals("large.bin", sorted.get(2).getName());
    }

    @Test
    void sortingKeepsOriginalDirectoryOrderAndHandlesEmptyDirectory() {
        Directory directory = new Directory("Документы");
        assertTrue(Main.sortFilesBySize(directory).isEmpty());
        directory.addFile("report.txt", 1200);
        directory.addFile("notes.txt", 500);
        List<Directory.FileInfo> original = directory.getFiles();
        List<Directory.FileInfo> sorted = Main.sortFilesBySize(directory);
        assertEquals(original, directory.getFiles());
        sorted.clear();
        assertEquals(original, directory.getFiles());
    }
}
