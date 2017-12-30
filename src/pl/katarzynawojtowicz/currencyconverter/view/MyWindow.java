package pl.katarzynawojtowicz.currencyconverter.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import pl.katarzynawojtowicz.currencyconverter.controller.CurrencyController;

public class MyWindow extends JFrame implements ActionListener {
	
	private JTextField amount;
	private JComboBox<String> sourceCurrency;
	private JComboBox<String> destinationCurrency;
	private JButton calculate;
	private JTextField result;
	
	private CurrencyController currencyController = new CurrencyController();


	public MyWindow() {
		super("Currency Converter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(200, 200);
		setSize(400, 400);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(150, 200, 150));
		
		JLabel amountLabel = new JLabel("Amount");
		amountLabel.setBounds(20, 20, 100, 25);
		panel.add(amountLabel);
		
		amount = new JTextField(5);
		amount.setBounds(20, 40, 100, 25);
		panel.add(amount);
		
		JLabel sourceCurrencyLabel = new JLabel("Source Currency");
		sourceCurrencyLabel.setBounds(20, 70, 200, 25);
		panel.add(sourceCurrencyLabel);
		
		sourceCurrency = new JComboBox<>(currencyController.getCurrencies());
		sourceCurrency.setBounds(20, 90, 100, 25);
		panel.add(sourceCurrency);
		
		JLabel destinationCurrencyLabel = new JLabel("Destination Currency");
		destinationCurrencyLabel.setBounds(20, 120, 200, 25);
		panel.add(destinationCurrencyLabel);
		
		destinationCurrency = new JComboBox<>(currencyController.getCurrencies());
		destinationCurrency.setBounds(20, 140, 100, 25);
		panel.add(destinationCurrency);
		
		calculate = new JButton("Calculate");
		calculate.setBounds(20, 190, 100, 25);
		calculate.addActionListener(this);
		panel.add(calculate);
		
		result = new JTextField(5);
		result.setBounds(140, 190, 100, 25);
		panel.add(result);
		result.setEditable(false);
		result.setEnabled(true);
		
		
		
		add(panel);
		setVisible(true);
		
	}




	@Override
	public void actionPerformed(ActionEvent e) {
		if(e. getSource() == calculate) {
			double amountValue = Double.parseDouble(amount.getText());
			String sourceObject = (String)sourceCurrency.getSelectedItem();
			String destinatonObject = (String)destinationCurrency.getSelectedItem();
			
			amountValue = currencyController.convert(amountValue, sourceObject, destinatonObject);
			
			String resultText = String.format("%.2f", amountValue);
			result.setText(resultText);
			System.out.println(result);
		}
		
	}

}
