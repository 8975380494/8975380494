package com.masai.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.FirNotFound;
import com.masai.model.Fir;
import com.masai.repository.FirDAO;

@Service
public class FirServiceImpl implements FirService{

	@Autowired
	private FirDAO fdao;
	 
	@Override
	public Fir RegisterFir(Fir fir) throws FirNotFound {
		// TODO Auto-generated method stub
		List<Fir> firlist = fdao.findAll();

		for(Fir i:firlist ) {
			if(fir.getFirId() == i.getFirId()) {
				throw new FirNotFound(" fir is already registered with the firid");
			}
		}
		return fdao.save(fir);
	}

}
