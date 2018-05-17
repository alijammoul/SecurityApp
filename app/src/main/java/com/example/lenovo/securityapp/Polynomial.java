package com.example.lenovo.securityapp;

/**
 * Created by Lenovo on 04/12/2017.
 */
public class Polynomial {
/*
 * Galois Fields GF(2^m) are equivalent to polynomials of degree less than m and of coefficient 2.
 * Hence, the coefficients of GF(2^m) can be represented with binary numbers of m bits
 *
 * We should be able to perform the following operations in GF(2^m):
 * 1) Modular reduction
 * 2) Addition
 * 3) Subtraction
 * 4) Inverse
 * 5) Multiplication
 * 6) Division
 */

    int value()
    {
        return Integer.parseInt(coeffs.getBits().toString());
    }

    BitString getCoeffs()
    {
        return new BitString(coeffs);
    }

    public String toString()
    {
        return coeffs.toString();
    }

    //Function add
    Polynomial add(Polynomial poly)
    {
        BitString answer = coeffs.xor(poly.getCoeffs());
        Polynomial result = new Polynomial(answer);

        return result;
    }


    //Function subtract
    Polynomial subtract(Polynomial poly)
    {
        BitString answer = coeffs.xor(poly.getCoeffs());
        Polynomial result = new Polynomial(answer);

        return result;
    }

    Polynomial inverse()
    {
        //To calculate the inverse of a polynomial we will use Eucledian algorithm

        Polynomial a1,a2;
        Polynomial b1,b2;

        Polynomial temp,coef,temp2;

        coeffs.trim();

        if (coeffs.getBits().toString().equals("0"))
        {
            return new Polynomial("0");
        }

        a1 = new Polynomial("00000000");
        b1 = new Polynomial("00000001");

        a2 = new Polynomial(primary);
        b2 = new Polynomial(coeffs);

        //Polynomial prime = new Polynomial(primary);


        while (b2.value() != 1) //while b2 != 1
        {

            //A1 = B1, A2 = B2

            temp2= new Polynomial(a1.coeffs);
            a1.coeffs = b1.getCoeffs();


            //B2 = A1- q*B1
            coef = a2.longDivisionBy(b2); //change value of B2


            coef = b1.multiply(coef);

            b1 = temp2.subtract(coef);


            temp = new Polynomial (a2.coeffs);

            a2.coeffs = b2.getCoeffs();
            b2.coeffs = temp.getCoeffs();


            b2.coeffs.trim();

            System.out.println("A1  " + a1 + "  A2  " + a2);
            System.out.println("B1  " + b1 + "  B2  " + b2);

        }

        return b1;
    }


    //Function multiply
    Polynomial multiply(Polynomial poly)
    {
        //To multiply two numbers in GF fields, we can multiply and reduce bit by bit
        //In order to do so, we need to find the remainder of x^n modulo the primary polynomial

        BitString remainder = primary.remainder(); //Remainder of x^n
        BitString temp = poly.getCoeffs();

        BitString result = new BitString("0");



        int overflow;


        for (int i=degree-1; i>=0; i--)
        {
            temp.fill(degree);

            //Check whether this temp should be included in the result (using xor) or not
            //(Depends on whether the bit is 1 or 0)
            if (coeffs.getBit(i) == 1)
            {
                result = result.xor(temp);


            }


            overflow = temp.shiftLeft();


            if (overflow == 1) //Overflow! need to xor with remainder
            {
                temp = temp.xor(remainder);
            }



        }

        Polynomial answer = new Polynomial(result);

        return answer;
    }



    //Function reduction to calculate the modular reduction of a number
    //Reduction reduces the polynomial itself and returns the quotient
    //(Long division)
    Polynomial reduce()
    {
        //We know that (a+b) mod n = (a mod n + b mod n) mod n
        //Hence, we can divide the polynomial into serevral "terms",
        //Reduce them one by one, then XOR them
        //The terms will be:
        //a0: all terms of degree less than n. Their reduction will give a0
        //ai : x^i i going from n to the degree of the polynomial.
        //Their reduction will give the terms of the irreducible polynomial with degree less than n times(x^(i-n))
        //Which is equivalent to i-n left shifts

	/*	BitString s = new BitString();
		BitString bits = primary.remainder();
		BitString quotient = new BitString("0");

		BitString x = new BitString("1");



		if (coeffs.getSize() <= degree)
			return new Polynomial(coeffs);

		for (int i = coeffs.getSize()-1; i>=0; i--)
		{
			if (i> coeffs.getSize()-degree)
			{
				s.insert( coeffs.getBit(i));
			}
			else
			{
				if (coeffs.getBit(i) == 1)
				{
					s = s.xor(bits);
					quotient = quotient.xor(x);
				}

				bits.shiftLeft();
				x.shiftLeft2();
			}

			System.out.println(quotient);

		}

		*/

        //coeffs = s;
        //return new Polynomial(quotient);

        return longDivisionBy(new Polynomial(primary));


    }

    Polynomial divide(Polynomial poly)
    {
        return this.multiply(poly.inverse());
    }

    private Polynomial longDivisionBy(Polynomial y)
    {
        //To divide two polynomials we need to use long division
        //Steps: Keep shifting y until x and y are of the same size
        //Substract the results (xor)
        //Repeat until y is bigger than x; x is the remainder

        //Only used in class


        BitString dividend = new BitString(coeffs);
        BitString divisor = y.getCoeffs();

        //Get rid of leading 0s
        dividend.trim();
        divisor.trim();

        BitString temp;
        BitString x;

        BitString result = new BitString("0");

        while (divisor.getSize() <= dividend.getSize())
        {
            temp = new BitString(divisor);
            x = new BitString("1");

            while (temp.getSize() < dividend.getSize())
            {
                temp.shiftLeft2();
                x.shiftLeft2();
            }

            result = result.xor(x);
            dividend = dividend.xor(temp);


            dividend.trim();



        }

        coeffs = dividend;
        return new Polynomial(result);


    }



    Polynomial(){}

    Polynomial(BitString bits)
    {
        coeffs = new BitString(bits);
    }

    Polynomial (String bits)
    {
        coeffs = new BitString(bits);
    }


    private
    BitString coeffs;
    int degree = 8;
    BitString primary = new BitString("100011011");

}
