package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {

    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        schoolBooks = addElementToArray(schoolBooks, book);
        return true;
    }

    @Override
    public SchoolBook[] findByName(String name) {
        SchoolBook[] result = new SchoolBook[0];
        for (int i = 0; i < count(); i++) {
            if (schoolBooks[i].getName().equals(name)) {
                result = addElementToArray(result, schoolBooks[i]);
            }
        }
        return result;
    }

    @Override
    public boolean removeByName(String name) {
        if (count() < 1) {
            return false;
        }
        int newSize = 0;
        SchoolBook[] temp = new SchoolBook[count()];
        for (int i = 0; i < schoolBooks.length; i++) {
            if (!schoolBooks[i].getName().equals(name)) {
                temp[newSize++] = schoolBooks[i];
            }
        }
        if (newSize == count()) {
            return false;
        }
        schoolBooks = new SchoolBook[newSize];
        System.arraycopy(temp, 0, schoolBooks, 0, newSize);
        return true;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }


    private SchoolBook[] addElementToArray(SchoolBook[] scr, SchoolBook newElement) {
        SchoolBook[] temp = new SchoolBook[scr.length + 1];
        System.arraycopy(scr, 0, temp, 0, scr.length);
        temp[temp.length - 1] = newElement;
        return temp;
    }
}
