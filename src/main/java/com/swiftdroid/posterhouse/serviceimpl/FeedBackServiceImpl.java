package com.swiftdroid.posterhouse.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swiftdroid.posterhouse.model.FeedBack;
import com.swiftdroid.posterhouse.repo.FeedBackRepository;
import com.swiftdroid.posterhouse.service.FeedBackService;

@Service
public class FeedBackServiceImpl implements FeedBackService {

	@Autowired
	private FeedBackRepository feedBackRepository;
	
	@Override
	public FeedBack saveFeedback(FeedBack feedBack) {
		// TODO Auto-generated method stub
		return feedBackRepository.save(feedBack);
	}

}
