package ConjuroNet;

import Lib.Consts;

import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.Hashtable;

public class ConjuroMsg implements Consts, java.io.Serializable {
    private ArrayList<Object> Objs = new ArrayList<Object>();
    private Class<?> type;
    
    public ConjuroMsg(Class<?> pType) {
    	type = pType;
    }

    
    public void addObject(Object pObj){
    	Objs.add(pObj);
    }
	public ArrayList<Object> getobjs() {
		return Objs;
	}

	public void setobjs(ArrayList<Object> pObjs) {
		this.Objs = pObjs;
	}


	public ArrayList<Object> getObjs() {
		return Objs;
	}


	public void setObjs(ArrayList<Object> objs) {
		Objs = objs;
	}


	public Class<?> getType() {
		return type;
	}


	public void setType(Class<?> type) {
		this.type = type;
	}













}
