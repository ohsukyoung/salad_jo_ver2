import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;

class ProductTypeChange {   // userSelect를 ProductType으로 변환
    public ProductType ProductTypeChange(int userSelect) {
        switch (userSelect) {
            case 1:
                return ProductType.RCMND;
            case 2:
                return ProductType.MY_SALAD;
            case 3:
                return ProductType.DRINK;
            case 4:
                return ProductType.SIDE;
            case 5:
                return ProductType.S_BASE;
            case 6:
                return ProductType.S_MAIN;
            case 7:
                return ProductType.S_SIDE;
            case 8:
                return ProductType.S_SOURCE;
            default:
                return null;
        }
    }
}