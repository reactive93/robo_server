package sftp

/**
 * Created by reactive on 22.04.2017.
 */

class MathExcec {

    companion object {
        @JvmStatic
        fun maxValue( datas:ArrayList<Int>):Int
        {
            var max=datas[0]
            for (i in datas)
            {
                if (max<i)
                {
                    max=i
                }
            }
            return max
        }
        @JvmStatic
        fun minValue( datas:ArrayList<Int>):Int
        {
            var min=datas[0]
            for (i in datas)
            {
                if (min>i)
                {
                    min=i
                }
            }
            return min
        }

        fun AllEval(datas:ArrayList<Int>):Array<Int>
        {
            var min=datas[0]
            var max=datas[0]
            var summ=0
            var avg=0
            for(i in datas)
            {
                summ+=i
                if(min>i)
                {
                    min=i
                }
                if(max<i)
                {
                    max=i
                }
            }
            avg=summ/datas.size
            return arrayOf(min,max,avg)
        }

    }


}