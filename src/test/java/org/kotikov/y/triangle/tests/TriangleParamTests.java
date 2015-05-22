package org.kotikov.y.triangle.tests;

import static junitparams.JUnitParamsRunner.$;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junitparams.Parameters;
import static junitparams.JUnitParamsRunner.$;
 
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.*;
import org.kotikov.y.triangle.Rtriangle;
import org.kotikov.y.triangle.RtriangleProvider;
import org.kotikov.y.triangle.Triangle;
import org.kotikov.y.triangle.WrongCoordinatesException;
import org.kotikov.y.triangle.WrongTriangleException;

import junitparams.JUnitParamsRunner;

/**
 * @author ak Котиков Александр
 * 
 * Тесты для проверки метода  getRtriangle()
 * Метод должен гарантированно возвращать объект прямоугольный треугольник или вызывать ошибку.
 * 
 * 
 * Был создан набор валидных и не валидных данных для проверки метода в
 * параметризированных Junit тестах. Тесты запускаются в отдельных методах с соответствующими названиями
 * и комментариями.
 * Тестовые данные подготовлены в системе QCAD (linux)
 * 
 * 
 * Формат тестов:
 *    
 *    $( входные данные ,   "описание, которое поможет программисту для отладки")) 
 *  
 *  При тестировании используется класс TrianglesDataGerator со статическим методом dataPrepare
 *  Он берет первоначальные тестовые данные, которые задаются в методе positiveTestsData
 *  И на их основе генерирует тестовые данные:
 *  Он задает один и тот же треугольник разными способами: перемешивает координаты, зеркально отражает
 *  треугольники и помещает их во все 4 четверти системы координат, там поворачивает их на 90, 180 и 270
 *  градусов. Соотношения сторон и углы треугольника при этом не меняются. Таким образом тестировщик
 *  должен думать только над первоначальным набором тестовых данных, а все что можно сгенерировать 
 *  автоматически- выполняет данный класс.
 *  
 *  Класс-генератор  использует простые вычисления и минимум логики.
 *  
 *  Генерация тестов:
 *  dataset = это список входных данных (каждая строчка здесь - это отдельный треугольник)
 *   
 *  finalDataset = (dataset +  (dataset * 6)) * 16  
 *  таким образом для одного треугольника будет сгенерировано  112 тестов
 *  
 *  При генерации методов класс добавляет отладочную информацию к треугольникам (например на сколько градусов
 *  он был повернут или была ли выполнено перемешивание координат) которую можно видеть в отчете JUnit, что 
 *  призвано ускорить процесс отладки программистом.
 *  
  
 */

@RunWith(JUnitParamsRunner.class)
public class TriangleParamTests {

 
    public static Object[] positiveTestsData(){
    	
    	 
    	
    	Object[]  dataset= $( 
    		/*
   	         Треугольники выбираться исходя из правил тестирования ПО  
       		 Если представить что сторона треугольника это значение то, нужно проверить	
       		 Различные соотношения сторон и углов треугольников: минимальный, средний, максимальный
       		 Так же используются различные размеры, чтобы проверить точность вычисления (от минимального до максимально возможного)	
       		*/
       			
   	 		 $( 6, 2,  6, 7,  2, 2, "треугольник с листа бумаги")
   	 		 
   	 		//Средний размер
   	        ,$(39, 26, 33, 20, 33, 26 , "Равносторонний. Area=18. per=20.49, Angles= 45, 45 and 90")  
   	        
   	            //TODO  Добавить тест с минимально возможным углом
   	        ,$(-1,1, 94999908, 1, -1,0 , "Минимально возможный угол   angles= 0.000000853774 , 90, 89.999999695235 ")  
   	        ,$(33, 17, 33, 11 ,36, 17 , "Среднее значение стороны ; angles=90 ,26.57 and 63.43, area=9, per =15.71") 
   	       
   	        
   	         //Крупные. Для проверки точности измерений
   	    	,$(-39000, -26000, -33000, -20000, -33000, -26000 , "Равносторонний. Area=18. per=20.49, Angles= 45, 45 and 90")  
   	        ,$(-300000, 320000, -300000,310000, -400000, 310000 , "Минимальная и максимальная сторона; area=5, per=21.05, Angles= 5.71, 90.00 and 84.29")  
   	        ,$(33000000, -17000000, 33000000, -11000000 ,36000000, -17000000, "Средняя сторона ; angles=90 ,26.57 and 63.43, area=9, per =15.71") 
   	        
   	         //Почти максимальный. Находящийся в центре
   	        ,$(-1000000000,1000000000 ,-1000000000,-2000000000,2000000000,1000000000, "Равносторонний. Близкий к макс.размеру integer, angles=90, 45 and 45, area=4500000000000000000, per=10242640687.12")
   	   	
    	    
    	);
    	//ИТОГО: 896 тестов
    
    	    //dataset = (dataset +  (dataset * 6)) * 16  
    	    //one triangle = 112 tests
    	 return  TrianglesDataGenerator.dataPrepare((Object[]) dataset) ;
    }
    
    
    
    
    @Test
    @Parameters( method = "positiveTestsData") 
    public void testPositiveCases(int X1,int Y1, int X2, int Y2, int X3, int Y3 , String str ) throws WrongTriangleException, WrongCoordinatesException {
    	
    	RtriangleProvider.setRtectangle(X1, Y1, X2, Y2, X3, Y3);
    	assertThat((Triangle)RtriangleProvider.getRtriangle(), equalTo(new Triangle(X1, Y1, X2, Y2, X3, Y3)));
     	 
    }
  
    //TODO 
    //1 негативные тесты
    
    //2 Тесты треугольника в системе координат
    
}