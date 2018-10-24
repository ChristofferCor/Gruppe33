package util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Simon Holland Flarup
 */
public class TextFormater {

    private String[] str, head;
    private int width;
    private char frame, lid;
    private String enclosed;
    private int length;

    public TextFormater(int width, char frame, char lid) {
        this.width = width;
        this.frame = frame;
        this.lid = lid;
    }

    public boolean print() {
        if (this.str == null) {
            return false;
        }

        if (this.head == null) {
            overflowPrevent();
            topBar();
            body();
            bottomBar();
            return true;
        }
        overflowPrevent();
        topBar();
        centerHeader();
        middelBar();
        body();
        bottomBar();
        return true;
    }

    public void setFrame(char c) {
        this.frame = c;
    }

    public void setLid(char c) {
        this.lid = c;
    }

    public void setBoth(String[] body, String[] head) {
        setBody(body);
        setHead(head);
    }

    public void setBody(String[] str) {
        if (str[0] != null) {
            this.str = str;
        } else {
            this.str = new String[1];
            this.str[0] = "";
        }
    }
    
    public void setBody(String str) {
        this.str = new String[1];
        if (str != null) {
            this.str[0] = str;
        } else {
            this.str[0] = "";
        }
    }

    public void setHead(String[] str) {
        if (str[0] != null) {
            this.head = str;
        } else {
            this.head = new String[1];
            this.head[0] = "";
        }
    }

    private void overflowPrevent() {
        for (String str : this.str) {
            if (str.length() >= this.width) {
                this.width = str.length() + 2;
                if (this.width % 2 != 0) {
                    this.width++;
                }
            }
        }
    }

    private void topBar() {
        this.enclosed = "";
        for (int i = 0; i < this.width; i++) {
            this.enclosed += this.lid;
        }
        System.out.printf("%1s%" + this.width + "s%1s\n", this.frame, this.enclosed, this.frame);
    }

    private void middelBar() {
        System.out.printf("%1s%" + this.width + "s%1s\n", this.frame, this.enclosed, this.frame);
    }

    private void bottomBar() {
        System.out.printf("%1s%" + this.width + "s%1s\n\n", this.frame, this.enclosed, this.frame);
    }

    private void body() {
        for (String str : this.str) {
            int length = centerLength(str);
            int sep = (this.width - length) / 2;
            System.out.printf("%1s%" + sep + "s%-" + length + "s%" + sep + "s%1s\n", this.frame, "", str, "", this.frame);
        }
    }

    private int centerLength(String str) {
        int length = str.length();
        if (length % 2 != 0 && length != 0) {
            length++;
        } else if (length == 0) {
            length += 2;
        }
        return length;
    }

    private void centerHeader() {
        int numHead = this.head.length <= 3 ? this.head.length : 1;
        int sep;
        int length;
        switch (numHead) {
            case 1:
                length = centerLength(head[0]);
                sep = (this.width - length) / 2; //Creates the seperators/spacers that centers the header.
                System.out.printf("%1s%" + sep + "s%-" + length + "s%" + sep + "s%1s\n", this.frame, "", head[0], "", this.frame);
                break;
            case 2:
                sep = (this.width) / 2; //Centers the header for a dual string header by right and left aligning.
                System.out.printf("%1s%-" + sep + "s%" + sep + "s%1s\n", this.frame, head[0], head[1], this.frame);
                break;
            case 3:
                length = centerLength(head[1]);
                sep = (this.width) / 4;
                int offSep = sep;
                if (sep % 2 != 0){
                    offSep++;
                }
                int cSep = ((sep * 2) - length)/2;
                System.out.printf("%1s%-" + offSep + "s%" + cSep + "s%" + length + "s%" + cSep + "s%" + offSep + "s%1s\n", this.frame, head[0], "", head[1], "", head[2], this.frame);
                break;
        }

    }
}
