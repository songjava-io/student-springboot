package com.example.controller.form;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.example.validation.ValidationSteps;

import lombok.Data;

@Data
@GroupSequence({
	BoardSaveForm.class,
	ValidationSteps.Step1.class,
	ValidationSteps.Step2.class,
	ValidationSteps.Step3.class,
	ValidationSteps.Step4.class,
	ValidationSteps.Step5.class
})
public class BoardSaveForm {

	private int boardSeq;
	
	@NotEmpty(groups = ValidationSteps.Step1.class, message = "{BoardSaveForm.boardType.notEmtpy}")
	private String boardType;

	@NotEmpty(groups = ValidationSteps.Step2.class, message = "{BoardSaveForm.userName.notEmtpy}")
	private String userName; // 회원 이름
	
	@NotEmpty(groups = ValidationSteps.Step3.class, message = "{BoardSaveForm.title.notEmtpy}")
	@Length(groups = ValidationSteps.Step4.class, min = 5, max = 20, message = "{BoardSaveForm.title.Length}")
	private String title;
	
	@NotEmpty(groups = ValidationSteps.Step5.class, message = "{BoardSaveForm.contents.notEmpty}")
	private String contents;
	
	
}
