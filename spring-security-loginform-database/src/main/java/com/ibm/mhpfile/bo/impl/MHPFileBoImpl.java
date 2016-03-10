package com.ibm.mhpfile.bo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.mhpfile.bo.MHPFileBo;
import com.ibm.mhpfile.dao.MHPFileDao;
import com.ibm.mhpfile.model.MHPFile;

@Service("MHPFileBo")
public class MHPFileBoImpl implements MHPFileBo{
	@Autowired
	MHPFileDao MHPFileDao;


	public void setMHPFileDao(MHPFileDao MHPFileDao) {
		this.MHPFileDao = MHPFileDao;
	}
	@Override
	public void save(MHPFile MHPFile) {
		MHPFileDao.save(MHPFile);
		
	}

	@Override
	public void update(MHPFile MHPFile) {
		MHPFileDao.update(MHPFile);
	}

	@Override
	public void delete(MHPFile MHPFile) {
		MHPFileDao.delete(MHPFile);
	}



	@Override
	public MHPFile findMHPFileByDate(String date) {
		return MHPFileDao.findMHPFileBydate(date);
	}

	@Override
	public Boolean getMHPFile(String date) {
		MHPFile MHPFile = findMHPFileByDate(date);
		if(MHPFile==null){
			MHPFile = new MHPFile(date);
			MHPFileDao.save(MHPFile);
			return true;
		}
		return false;
	}
	
}
