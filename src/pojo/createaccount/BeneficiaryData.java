/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo.createaccount;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author dev09
 */

@XmlRootElement(name = "BeneficiaryData")
@XmlAccessorType (XmlAccessType.FIELD)
class BeneficiaryData {
    
    @XmlElement(name = "AccO1")
    private List<AccO1> acco1;
    @XmlElement(name = "AccO2")
    private List<AccO2> acco2;
    @XmlElement(name = "AccO3")
    private List<AccO3> acco3;
    @XmlElement(name = "AccO4")
    private List<AccO4> acco4;
    @XmlElement(name = "AccO5")
    private List<AccO5> acco5;

    public List<AccO1> getAcco1() {
        return acco1;
    }

    public void setAcco1(List<AccO1> acco1) {
        this.acco1 = acco1;
    }

    public List<AccO2> getAcco2() {
        return acco2;
    }

    public void setAcco2(List<AccO2> acco2) {
        this.acco2 = acco2;
    }

    public List<AccO3> getAcco3() {
        return acco3;
    }

    public void setAcco3(List<AccO3> acco3) {
        this.acco3 = acco3;
    }

    public List<AccO4> getAcco4() {
        return acco4;
    }

    public void setAcco4(List<AccO4> acco4) {
        this.acco4 = acco4;
    }

    public List<AccO5> getAcco5() {
        return acco5;
    }

    public void setAcco5(List<AccO5> acco5) {
        this.acco5 = acco5;
    }
    
    
}
