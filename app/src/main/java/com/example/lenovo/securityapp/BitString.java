package com.example.lenovo.securityapp;

/**
 * Created by Lenovo on 04/12/2017.
 */

public class BitString {

	/*
	 * Because the number of bits needed to represent GF(2^163) or GF(2^233) are more than integers or longs can handle,
	 * we need to represent them as a string.
	 * To simplify operations on that string (because these operations will be used often),
	 * we implement a class BitString to handle it.
	 *
	 *
	 * Operations we need are:
	 * 1) Shift left
	 * 2) XOR
	 *
	 */


    StringBuilder getBits(){return new StringBuilder(bits);}

    int getSize()
    {return bits.length();}

    int getBit(int i)
    {

        char c = bits.charAt(i);
        int result = c - '0';

        return result;
    }

    void trim()
    {
        if (bits.indexOf("1") >= 0)
        {
            bits.delete(0,bits.indexOf("1"));
        }
        else
        {
            bits = new StringBuilder("0");
        }

    }

    void fill (int degree)
    {
        //Insert leading 0s to get the desired size

        while (bits.length() < degree)
            bits.insert(0, "0");
    }


    void insert ( int s)
    {
        char c = (char) (s + '0');
        bits.insert(0,s );
    }

    BitString remainder()
    {
        StringBuilder temp = new StringBuilder(bits);
        temp.deleteCharAt(0);

        BitString str = new BitString(temp);
        return str;

    }

    BitString xor(BitString s)
    {

        BitString result;


        StringBuilder op1 = bits;
        StringBuilder op2 = s.getBits();
        StringBuilder answer = new StringBuilder();

        //In case one operand is bigger than the other,
        //we add leading zeros to the smaller one until both operands have the same size
        if (op1.length() > op2.length())
        {
            for (int i= op2.length(); i < op1.length();i++) //second operand is smaller
                op2 = op2.insert(0,"0");


        }
        else if (op1.length() < op2.length())
        {
            for (int i = op1.length(); i< op2.length(); i++)
                op1 = op1.insert(0, "0");
        }


        //Now that both string builders have the same size, we can xor them bit by bit without problems
        for (int i=0; i<op1.length(); i++)
        {
            if (op1.charAt(i)== op2.charAt(i))
            {
                answer.append("0");
            }
            else
            {
                answer.append("1");
            }

        }

        result = new BitString(answer);
        return result;


    }


    int shiftLeft()
    {
        //Function shiftLeft left shifts the BitString and returns the overflowed bit

        char carry;

        bits.append("0");
        carry = bits.charAt(0);
        bits.deleteCharAt(0);

        return carry - '0';

    }

    void shiftLeft2()
    {
        //Function shiftLeft that handles overflow;
        //basically its just appending 0 at the end

        bits.append("0");
    }


    public String toString()
    {
        return bits.toString();
    }




    //Constructors:
    BitString()
    {bits = new StringBuilder();}


    BitString(StringBuilder s)
    {bits = new StringBuilder(s);}

    BitString(String s)
    {bits = new StringBuilder(s);}


    //Copy constructor that will be used for temporary variables
    BitString(BitString object)
    {bits = new StringBuilder(object.getBits());}



    private
    StringBuilder bits;


}
