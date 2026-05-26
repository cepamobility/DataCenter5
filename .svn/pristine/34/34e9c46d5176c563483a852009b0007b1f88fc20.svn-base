/**
 * Created on 14-nov-2005
 * CEPA
 * DataCenter 5
 */
package persistence.administration.corporation.tables.test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import persistence.administration.corporation.tables.Corporation;
import persistence.administration.corporation.tables.DivOrg;
import persistence.administration.corporation.tables.DivOrgItemLevel1;
import persistence.administration.corporation.tables.LevelLabel;

import communication.administration.corporation.tables.CorporationData;
import communication.administration.corporation.tables.DivOrgData;
import communication.administration.corporation.tables.ItemData;
import communication.administration.corporation.tables.LabelItemData;

/**
 * @author jrodriguez
 */
public class CorporationTest {

    public static void main(String[] args) {
        test1();
        test2();
    }
    
    private static void test1() {
        Corporation corporation = new Corporation();
        corporation.setId(new Integer(1));
        corporation.setName("CEPA");
        
        System.out.println("-----------------TEST 1------------------");
        CorporationData data = corporation.getData();
        System.out.println("Id "+data.getId());
        System.out.println("Name "+data.getName());
        
        Corporation corporation2 = new Corporation(data);
        System.out.println("Id "+corporation2.getId());
        System.out.println("Name "+corporation2.getName());
        System.out.println("-----------------------------------------");
    }

    private static void test2() {
        Corporation corporation = new Corporation();
        corporation.setId(new Integer(1));
        corporation.setName("CEPA");
        
        Set divOrgSet = new HashSet();
        DivOrg divOrg = new DivOrg();
        divOrg.setId(new Integer(1));
        divOrg.setResourceKey("Divisiones");
        
        Set labels = new HashSet();
        labels.add(new LevelLabel(new Integer(1),null,"Division",new Integer(1),null));
        divOrg.setLevelLabelSet(labels);
        
        Set items = new HashSet();
        items.add(new DivOrgItemLevel1(new Integer(1),divOrg,"Division 1",null,false));
        divOrg.setItemLevel1Set(items);
        divOrgSet.add(divOrg);
        corporation.setDivOrgSet(divOrgSet);
        
        System.out.println("-----------------TEST 2------------------");
        CorporationData data = corporation.getData();
        System.out.println("Id "+data.getId());
        System.out.println("Name "+data.getName());
        Iterator it = data.getDivOrgDataSet().iterator();
        while(it.hasNext()) {
            DivOrgData divOrgData = (DivOrgData)it.next();
            System.out.println(divOrgData.getName());
            LabelItemData lid = divOrgData.getDivOrgLevels();
            System.out.println(" |--> "+lid.getName());
            Iterator it2 = lid.getItems().iterator();
            while(it2.hasNext()) {
                System.out.println(" 	|--> "+((ItemData)it2.next()).getName());
            }
        }
        
        Corporation corporation2 = new Corporation(data);
        System.out.println("Id "+corporation2.getId());
        System.out.println("Name "+corporation2.getName());
        System.out.println("-----------------------------------------");
    }
}
