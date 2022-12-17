package com.masai.service;

import com.masai.exception.FirNotFound;
import com.masai.model.Fir;

public interface FirService {

	public Fir RegisterFir(Fir fir) throws FirNotFound ;
}
