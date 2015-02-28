package edu.uta.tdj.code.proposal;

import java.util.List;

import javax.swing.JButton;

public interface ComputedElement {
	public List<JButton> getButtons(ProposalButtonFactory pcComputer);
}
