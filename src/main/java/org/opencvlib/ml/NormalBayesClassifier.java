
//
// This file is auto-generated. Please don't modify it!
//
package org.opencvlib.ml;

import java.lang.String;
import org.opencvlib.core.Mat;

// C++: class NormalBayesClassifier
//javadoc: NormalBayesClassifier
public class NormalBayesClassifier extends StatModel {

    protected NormalBayesClassifier(long addr) { super(addr); }


    //
    // C++: static Ptr_NormalBayesClassifier create()
    //

    //javadoc: NormalBayesClassifier::create()
    public static NormalBayesClassifier create()
    {
        
        NormalBayesClassifier retVal = new NormalBayesClassifier(create_0());
        
        return retVal;
    }


    //
    // C++: static Ptr_NormalBayesClassifier load(String filepath, String nodeName = String())
    //

    //javadoc: NormalBayesClassifier::load(filepath, nodeName)
    public static NormalBayesClassifier load(String filepath, String nodeName)
    {
        
        NormalBayesClassifier retVal = new NormalBayesClassifier(load_0(filepath, nodeName));
        
        return retVal;
    }

    //javadoc: NormalBayesClassifier::load(filepath)
    public static NormalBayesClassifier load(String filepath)
    {
        
        NormalBayesClassifier retVal = new NormalBayesClassifier(load_1(filepath));
        
        return retVal;
    }


    //
    // C++:  float predictProb(Mat inputs, Mat& outputs, Mat& outputProbs, int flags = 0)
    //

    //javadoc: NormalBayesClassifier::predictProb(inputs, outputs, outputProbs, flags)
    public  float predictProb(Mat inputs, Mat outputs, Mat outputProbs, int flags)
    {
        
        float retVal = predictProb_0(nativeObj, inputs.nativeObj, outputs.nativeObj, outputProbs.nativeObj, flags);
        
        return retVal;
    }

    //javadoc: NormalBayesClassifier::predictProb(inputs, outputs, outputProbs)
    public  float predictProb(Mat inputs, Mat outputs, Mat outputProbs)
    {
        
        float retVal = predictProb_1(nativeObj, inputs.nativeObj, outputs.nativeObj, outputProbs.nativeObj);
        
        return retVal;
    }


    @Override
    protected void finalize() throws Throwable {
        delete(nativeObj);
    }



    // C++: static Ptr_NormalBayesClassifier create()
    private static native long create_0();

    // C++: static Ptr_NormalBayesClassifier load(String filepath, String nodeName = String())
    private static native long load_0(String filepath, String nodeName);
    private static native long load_1(String filepath);

    // C++:  float predictProb(Mat inputs, Mat& outputs, Mat& outputProbs, int flags = 0)
    private static native float predictProb_0(long nativeObj, long inputs_nativeObj, long outputs_nativeObj, long outputProbs_nativeObj, int flags);
    private static native float predictProb_1(long nativeObj, long inputs_nativeObj, long outputs_nativeObj, long outputProbs_nativeObj);

    // native support for java finalize()
    private static native void delete(long nativeObj);

}
