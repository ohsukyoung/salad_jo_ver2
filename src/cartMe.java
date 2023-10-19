import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.ArrayList;
import java.util.List;

import java.util.HashMap;

import java.util.Date;
import java.text.SimpleDateFormat;

//장바구니 및 결제수단 클래스
class cartMe
{
    static final int e_del = 1; //장바구니 비우기
    static final int e_pay = 2; //결제
    static final int e_add = 3; //추가 주문
}