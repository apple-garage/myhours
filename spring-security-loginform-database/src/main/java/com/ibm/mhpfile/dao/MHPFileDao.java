package com.ibm.mhpfile.dao;

import com.ibm.mhpfile.model.MHPFile;

public interface MHPFileDao {
	
	void save(MHPFile MHPFile);
	
	void update(MHPFile MHPFile);
	
	void delete(MHPFile MHPFile);

	MHPFile findMHPFileBydate(String date);

}
