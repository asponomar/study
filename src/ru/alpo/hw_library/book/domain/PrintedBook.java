package ru.alpo.hw_library.book.domain;

public class PrintedBook extends Book {
    private String fontType;

    public String getFontType() {
        return fontType;
    }

    public void setFontType(String fontType) {
        this.fontType = fontType;
    }

    @Override
    public String toString() {
        return "PrintedBook{" + super.toString() +
                " fontType = '" + fontType + '\'' +
                '}';
    }
}
