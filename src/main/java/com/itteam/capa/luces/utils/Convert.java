package com.itteam.capa.luces.utils;

public class Convert {
    // method to convert binary to decimal
    public int binaryToDecimal(long binary)
    {

        // variable to store the converted binary
        int decimalNumber = 0, i = 0;

        // loop to extract digits of the binary
        while (binary > 0) {

            // extracting each digit of the binary
            // by getting the remainder of division
            // by 10 and multiplying it by
            // increasing integral powers of 2
            decimalNumber
                    += Math.pow(2, i++) * (binary % 10);

            // update condition of dividing the
            // binary by 10
            binary /= 10;
        }

        // returning the decimal
        return decimalNumber;
    }

    // method to convert decimal to hex
    public String decimalToHex(long binary)
    {

        // variable to store the output of
        // binaryToDecimal() method
        int decimalNumber = binaryToDecimal(binary);

        // character array to represent double
        // digit remainders
        char arr[] = { 'A', 'B', 'C', 'D', 'E', 'F' };

        // variable to store the remainder on
        // division by 16
        int remainder, i = 0;

        // declaring the string that stores the
        // final hex string
        String hexNumber = "";

        // loop to convert decimal to hex
        while (decimalNumber != 0) {

            // calculating the remainder of decimal
            // by dividing by 16
            remainder = decimalNumber % 16;

            // checking if the remainder is >= 10
            if (remainder >= 10)

                // replacing with the corresponding
                // alphabet from the array
                hexNumber = arr[remainder - 10] + hexNumber;

            else

                hexNumber = remainder + hexNumber;

            // update condition of dividing the
            // number by 16
            decimalNumber /= 16;
        }

        // returning the hex string
        return hexNumber;
    }



}
