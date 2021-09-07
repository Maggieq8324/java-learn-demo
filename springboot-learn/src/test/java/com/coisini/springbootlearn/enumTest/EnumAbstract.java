package com.coisini.springbootlearn.enumTest;

public enum EnumAbstract {
    MON {
        void getDetail() {
            System.out.println("Monday");
        }
    },
    TUE {
        void getDetail() {
            System.out.println("Tuesday");
        }
    },
    WED {
        void getDetail() {
            System.out.println("Wednesday");
        }
    };

    abstract void getDetail();

    public static void main(String[] args) {
        for (EnumAbstract ea : EnumAbstract.values()) {
            ea.getDetail();
        }
    }

}
