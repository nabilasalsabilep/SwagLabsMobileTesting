@QRCodeScanner
Feature: QR Code Scanner feature

  @SuccessfullyScanQRCode
  Scenario: User wants to successfully scan QR Code
    Given User opens swag labs application via mobile device
    And User input valid username
    And User input valid password
    And User tap Login button
    And User tap burger menu on the header
    When User tap QR CODE SCANNER sub menu
    Then User verifies that the system has successfully opens the URL address in the QR code