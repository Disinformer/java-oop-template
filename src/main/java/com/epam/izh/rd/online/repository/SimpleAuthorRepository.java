package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

public class SimpleAuthorRepository implements AuthorRepository {

    private Author[] authors = new Author[0];

    @Override
    public boolean save(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) != null) {
            return false;
        }
        Author[] temp = new Author[count() + 1];
        System.arraycopy(authors, 0, temp, 0, count());
        temp[temp.length - 1] = author;
        authors = temp;
        return true;
    }

    @Override
    public Author findByFullName(String name, String lastName) {
        for (int i = 0; i < count(); i++) {
            if ( authors[i].getName().equals(name) && authors[i].getLastName().equals(lastName) ) {
                return authors[i];
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (count() < 1){
            return false;
        }
        String name = author.getName();
        String lastName = author.getLastName();
        if (findByFullName(name, lastName) == null) {
            return false;
        }
        int j = 0;
        Author[] tempAuthors = new Author[count() - 1];
        for (int i = 0; i < count(); i++) {
            if (authors[i].getName().equals(name) && authors[i].getLastName().equals(lastName)) {
                continue;
            }
            tempAuthors[j++] = authors[i];
        }
        return true;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
