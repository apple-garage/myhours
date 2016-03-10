package com.ibm.mhpfile.bo;

import com.ibm.mhpfile.model.MHPFile;

public interface MHPFileBo {
	
	void save(MHPFile MHPFile);
	
	void update(MHPFile MHPFile);
	
	void delete(MHPFile MHPFile);
	
	Boolean getMHPFile(String date);
	
	MHPFile findMHPFileByDate(String date);
}
