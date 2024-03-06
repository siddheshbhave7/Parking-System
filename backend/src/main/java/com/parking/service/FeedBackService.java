package com.parking.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.repository.FeedBackRepository;
import com.parking.repository.Feedback;

@Service
@Transactional
public class FeedBackService {

	@Autowired
    private FeedBackRepository feedbackRepository;

    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    public Feedback getFeedbackById(Long id) {                                    //We are Not Going To add this
        return feedbackRepository.findById(id).orElse(null);
    }

    public Feedback createFeedback(Feedback feedback) {
        
        return feedbackRepository.save(feedback);
    }

    public Feedback updateFeedback(Long id, Feedback updatedFeedback) {            //We are Not Going To add this
        Feedback existingFeedback = feedbackRepository.findById(id).orElse(null);

        if (existingFeedback != null) {
            existingFeedback.setFeedbackText(updatedFeedback.getFeedbackText());
            existingFeedback.setEmail(updatedFeedback.getEmail());
            existingFeedback.setName(updatedFeedback.getName());
            existingFeedback.setSubject(updatedFeedback.getSubject());
            return feedbackRepository.save(existingFeedback);
        }

        return null; 
    }

    public void deleteFeedback(Long id) {
        feedbackRepository.deleteById(id);
        
    }

}
