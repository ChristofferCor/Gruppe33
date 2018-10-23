/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package semestreprojekt.fightsystem;

/**
 * This class is implemented wrong! It consists of no attributes or methods, but
 * just three constructors that have a bunch of redundant code. Be wary that
 * it's a prototype, and needs rewriting. Consider all constructors like methods
 * with no return type.
 *
 * @author Simon Holland Flarup
 */
public class CenterText {

    private String[] str;
    private int width;
    private char frame, lid;
    private String enclosed;

    public CenterText(String[] str, int width, char frame, char lid) {
        this.str = str;
        this.width = width;
        this.frame = frame;
        this.lid = lid;
    }

    public CenterText(String header, String[] str, int width, char frame, char lid) {
        this(str, width, frame, lid);
        overflowPrevent();
        topBar();

        int hLength = header.length();
        if (hLength % 2 != 0 && hLength != 0) {
            hLength++;
        } else if (hLength == 0) {
            hLength += 2;
        }
        int hSep = (this.width - hLength) / 2;
        System.out.printf("%1s%" + hSep + "s%-" + hLength + "s%" + hSep + "s%1s\n", "#", "", header, "", "#");

        System.out.printf("%1s%" + this.width + "s%1s\n", this.frame, this.enclosed, this.frame);

        for (String cStr : str) {
            int length = cStr.length();
            if (length % 2 != 0 && length != 0) {
                length++;
            } else if (length == 0) {
                length += 2;
            }
            int sep = (this.width - length) / 2;
            System.out.printf("%1s%" + sep + "s%-" + length + "s%" + sep + "s%1s\n", "#", "", cStr, "", "#");

        }

        System.out.printf("%1s%" + this.width + "s%1s\n", this.frame, this.enclosed, this.frame);
        System.out.println("");
    }

    public CenterText(String header1, String header2, String[] str, int width, char frame, char lid) {
        this(str, width, frame, lid);
        overflowPrevent();
        topBar();

        int hSep = (this.width) / 2;
        System.out.printf("%1s%-" + hSep + "s%" + hSep + "s%1s\n", "#", header1, header2, "#");

        System.out.printf("%1s%" + this.width + "s%1s\n", this.frame, this.enclosed, this.frame);

        for (String cStr : str) {
            int length = cStr.length();
            if (length % 2 != 0 && length != 0) {
                length++;
            } else if (length == 0) {
                length += 2;
            }
            int sep = (this.width - length) / 2;
            System.out.printf("%1s%" + sep + "s%-" + length + "s%" + sep + "s%1s\n", "#", "", cStr, "", "#");

        }

        System.out.printf("%1s%" + this.width + "s%1s\n", this.frame, this.enclosed, this.frame);
        System.out.println("");
    }

    private void overflowPrevent() {
        for (String cStr : this.str) {
            if (cStr.length() >= this.width) {
                this.width = cStr.length() + 2;
                if (this.width % 2 != 0) {
                    this.width++;
                }
            }
        }
    }

    private void topBar() {
        this.enclosed = "";
        for (int i = 0; i < this.width; i++) {
            this.enclosed = this.enclosed + this.lid;
        }
        System.out.printf("%1s%" + this.width + "s%1s\n", this.frame, this.enclosed, this.frame);
    }
}
