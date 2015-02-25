package edu.uta.tdj.code.proposal;

import java.util.List;

import javax.swing.JButton;

import edu.uta.tdj.factory.ProposalButtonFactory;

public interface ComputedElement {
	public List<JButton> getButtons(ProposalButtonFactory pcComputer);
}
