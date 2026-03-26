/*
 * Copyright 2025 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.specs

import org.openqa.selenium.By
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.verbs.ShouldVerb
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen}
import uk.gov.hmrc.selenium.webdriver.{Browser, ScreenshotOnFailure}
import uk.gov.hmrc.ui.pages.*
import uk.gov.hmrc.ui.pages.Vendor.*
import uk.gov.hmrc.ui.pages.Purchaser.*
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation
import uk.gov.hmrc.ui.tags.*

class PurchaserQuestionsSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing Frontend Purchaser Questions") {
    Scenario(
      "Complete the Purchaser Questions journey as an Individual and changed to a Company",
      PurchaserJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("no-purchaser"))
      When("the user clicks on the 'Purchaser Questions' link")
      ReturnTaskList.clickLinkById("task-list-link-purchaser-questions")
      Then("the user is navigated to the Before You Start page")
      PurchaserBeforeYouStart.verifyPageTitle(PurchaserBeforeYouStart.pageTitle)
      When("the user clicks the Continue button")
      PurchaserBeforeYouStart.saveAndContinue()
      Then("the user is navigated to the Who Is Making The Purchase page")
      PurchaserWhoIsMakingThePurchase.verifyPageTitle(PurchaserWhoIsMakingThePurchase.pageTitle)
      When("the user selects the 'An Individual' radio button")
      PurchaserWhoIsMakingThePurchase.radioButton(PurchaserWhoIsMakingThePurchase.individual)
      And("clicks the Save and continue button")
      PurchaserWhoIsMakingThePurchase.saveAndContinue()
      Then("the user is navigated to the What is the purchaser’s full name? page")
      PurchaserName.verifyPageTitle(PurchaserName.pageTitle)
      When("the user inputs their first name, middle name, and surname")
      PurchaserName.input(
        By.id(PurchaserName.forenameId),
        PurchaserName.forenameInput
      )
      PurchaserName.input(
        By.id(PurchaserName.middlenameId),
        PurchaserName.middlenameInput
      )
      PurchaserName.input(
        By.id(PurchaserName.surnameId),
        PurchaserName.surnameInput
      )
      And("clicks the Save and continue button")
      PurchaserName.saveAndContinue()
      Then("the user is navigated to the Address lookup page")
      PurchaserAddress.verifyPageTitle(PurchaserAddress.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAddress.clickAddressManually()
      And("enters their address manually")
      PurchaserAddress.verifyPageTitle(PurchaserAddress.editPageTitle)
      PurchaserAddress.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user is navigated to the Property Address page to 'Review and confirm the address'")
      PurchaserAddress.verifyPageTitle(PurchaserAddress.confirmPageTitle)
      When("the user clicks the Confirm address button")
      PurchaserAddress.clickContinueButton()
      Then("the user is navigated to the Do you want to add a phone number? page")
      DoesPurchaserHavePhoneNumber.verifyPageTitle(DoesPurchaserHavePhoneNumber.pageTitle)
      When("the user selects the 'No' radio button")
      DoesPurchaserHavePhoneNumber.radioButton(DoesPurchaserHavePhoneNumber.no)
      And("clicks the Save and continue button")
      DoesPurchaserHavePhoneNumber.saveAndContinue()
      Then("the user is navigated to the Does the Purchaser have a National Insurance number? page")
      AddPurchaserNationalInsuranceNumber.verifyPageTitle(AddPurchaserNationalInsuranceNumber.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddPurchaserNationalInsuranceNumber.radioButton(AddPurchaserNationalInsuranceNumber.yes)
      And("clicks the Save and continue button")
      AddPurchaserNationalInsuranceNumber.saveAndContinue()
      Then("the user is navigated to the What is the Purchaser's national insurance number? page")
      EnterPurchaserNationalInsuranceNumber.verifyPageTitle(EnterPurchaserNationalInsuranceNumber.pageTitle)
      When("the user inputs a National Insurance Number")
      EnterPurchaserNationalInsuranceNumber.input(
        By.id(EnterPurchaserNationalInsuranceNumber.Nino),
        EnterPurchaserNationalInsuranceNumber.NinoValue
      )
      And("clicks the Save and continue button")
      EnterPurchaserNationalInsuranceNumber.saveAndContinue()
      Then("the user is navigated to the What is the purchaser Date of Birth? page")
      DateOfBirth.verifyPageTitle(DateOfBirth.pageTitle)
      When("the user inputs their Date of Birth")
      DateOfBirth.enterDateOfBirth()
      And("clicks the Save and continue button")
      DateOfBirth.saveAndContinue()
      Then("the user is navigated to is the Purchaser Acting as a Trustee? page")
      ActingAsATrustee.verifyPageTitle(ActingAsATrustee.pageTitle)
      When("the user selects the 'Yes' radio button")
      ActingAsATrustee.radioButton(ActingAsATrustee.yes)
      And("clicks the Save and continue button")
      ActingAsATrustee.saveAndContinue()
      Then("the user is navigated to the Are the purchaser and vendor connected? page")
      PurchaserAndVendorConnected.verifyPageTitle(PurchaserAndVendorConnected.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserAndVendorConnected.radioButton(PurchaserAndVendorConnected.yes)
      And("clicks the Save and continue button")
      PurchaserAndVendorConnected.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the 'Change' link for purchaser name")
      PurchaserCheckYourAnswers.clickPurchaserNameChange()
      Then("the user is navigated to the What is the purchaser’s full name? page")
      PurchaserName.verifyPageTitle(PurchaserName.pageTitle)
      When("the user updates their name")
      PurchaserName.input(By.id(PurchaserName.surnameId), PurchaserName.surnameInput2)
      And("clicks the Save and continue button")
      PurchaserName.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the 'Change' link for National Insurance number")
      PurchaserCheckYourAnswers.clickPurchaserNINumberChange()
      Then("the user is navigated to the What is Purchaser's national insurance number? page")
      EnterPurchaserNationalInsuranceNumber.verifyPageTitle(EnterPurchaserNationalInsuranceNumber.pageTitle)
      When("the user updates their National Insurance Number")
      EnterPurchaserNationalInsuranceNumber.input(
        By.id(EnterPurchaserNationalInsuranceNumber.Nino),
        EnterPurchaserNationalInsuranceNumber.NinoValue2
      )
      And("clicks the Save and continue button")
      EnterPurchaserNationalInsuranceNumber.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the 'Change' link for date of birth")
      PurchaserCheckYourAnswers.clickPurchaserDOBChange()
      Then("the user is navigated to the What is the purchaser Date of Birth? page")
      DateOfBirth.verifyPageTitle(DateOfBirth.pageTitle)
      When("the user updates their Date of Birth")
      DateOfBirth.enterDateOfBirth()
      And("clicks the Save and continue button")
      DateOfBirth.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)

      When("the user clicks on the 'Change' link for purchaser type")
      PurchaserCheckYourAnswers.clickPurchaserTypeChange()
      Then("the user is navigated to the Who is making the purchase page")
      PurchaserWhoIsMakingThePurchase.verifyPageTitle(PurchaserWhoIsMakingThePurchase.pageTitle)
      When("the user selects the 'A Company' radio button")
      PurchaserWhoIsMakingThePurchase.radioButton(PurchaserWhoIsMakingThePurchase.company)
      And("clicks the Save and continue button")
      PurchaserWhoIsMakingThePurchase.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the link for entering company name")
      PurchaserCheckYourAnswers.clickPurchaserNameChange()
      Then("the user is navigated to the What is the company’s name? page")
      PurchaserName.verifyPageTitle(PurchaserName.pageTitle)
      When("the user inputs their company name")
      PurchaserName.input(
        By.id(PurchaserName.companyId),
        PurchaserName.companyName2
      )
      And("clicks the Save and continue button")
      PurchaserName.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the link for entering Form of ID")
      PurchaserCheckYourAnswers.clickFormOfIDChange()
      Then("the user is navigated to the Confirm Purchaser Identity page")
      ConfirmPurchaserIdentity.verifyPageTitle(ConfirmPurchaserIdentity.pageTitle)
      When("the user selects the 'VAT registration number' radio button")
      ConfirmPurchaserIdentity.radioButton(ConfirmPurchaserIdentity.vatRegistrationNumber)
      And("clicks the Save and continue button")
      ConfirmPurchaserIdentity.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the link for entering VAT registration number")
      PurchaserCheckYourAnswers.clickVATRegistrationNumberChange()
      Then("the user is navigated to the What is the purchaser's VAT Registration number page")
      VATRegistrationNumber.verifyPageTitle(VATRegistrationNumber.pageTitle)
      When("the user enters their VAT registration number")
      VATRegistrationNumber.input(
        By.id(VATRegistrationNumber.vat),
        VATRegistrationNumber.VATNumber
      )
      And("clicks the Save and continue button")
      VATRegistrationNumber.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the 'Change' link for do you know type of company")
      PurchaserCheckYourAnswers.clickDoYouKnowCompanyNameChange()
      Then("the user is navigated to the Do you know what type of company page")
      DoYouKnowTypeOfCompany.verifyPageTitle(DoYouKnowTypeOfCompany.pageTitle)
      When("the user selects the 'Yes' radio button")
      DoYouKnowTypeOfCompany.radioButton(DoYouKnowTypeOfCompany.yes)
      And("clicks the Save and continue button")
      DoYouKnowTypeOfCompany.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the link for entering type of company")
      PurchaserCheckYourAnswers.clickTypeOfCompanyChange()
      Then("the user is navigated to the What type of company? page")
      TypeOfCompany.verifyPageTitle(TypeOfCompany.pageTitle)
      When("the user selects 4 types of company")
      TypeOfCompany.checkbox(TypeOfCompany.Bank, true)
      TypeOfCompany.checkbox(TypeOfCompany.Building_Society, true)
      TypeOfCompany.checkbox(TypeOfCompany.Insurance_or_assurance_company, true)
      TypeOfCompany.checkbox(TypeOfCompany.Property_company, true)
      And("clicks the Save and continue button")
      TypeOfCompany.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)

      When("the user clicks the Save and continue button")
      PurchaserCheckYourAnswers.saveAndContinue()
      Then("the user is navigated to the Purchaser Overview page")
      PurchaserOverview.verifyPageTitle(PurchaserOverview.pageTitle)
    }

    Scenario(
      "Complete the Purchaser Questions journey as an Individual with no National Insurance Number",
      PurchaserJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("no-purchaser"))
      When("the user clicks on the 'Purchaser Questions' link")
      ReturnTaskList.clickLinkById("task-list-link-purchaser-questions")
      Then("the user is navigated to the Before you start page")
      PurchaserBeforeYouStart.verifyPageTitle(PurchaserBeforeYouStart.pageTitle)
      When("the user clicks the Continue button")
      PurchaserBeforeYouStart.saveAndContinue()
      Then("the user is navigated to the Who is making the purchase page")
      PurchaserWhoIsMakingThePurchase.verifyPageTitle(PurchaserWhoIsMakingThePurchase.pageTitle)
      When("the user selects the 'An Individual' radio button")
      PurchaserWhoIsMakingThePurchase.radioButton(PurchaserWhoIsMakingThePurchase.individual)
      And("clicks the Save and continue button")
      PurchaserWhoIsMakingThePurchase.saveAndContinue()
      Then("the user is navigated to the What is the purchaser’s full name? page")
      PurchaserName.verifyPageTitle(PurchaserName.pageTitle)
      When("the user inputs their first name, middle name, and surname")
      PurchaserName.input(
        By.id(PurchaserName.forenameId),
        PurchaserName.forenameInput
      )
      PurchaserName.input(
        By.id(PurchaserName.middlenameId),
        PurchaserName.middlenameInput
      )
      PurchaserName.input(
        By.id(PurchaserName.surnameId),
        PurchaserName.surnameInput
      )
      And("clicks the Save and continue button")
      PurchaserName.saveAndContinue()
      Then("the user is navigated to the Address lookup page")
      PurchaserAddress.verifyPageTitle(PurchaserAddress.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAddress.clickAddressManually()
      And("enters their address manually")
      PurchaserAddress.verifyPageTitle(PurchaserAddress.editPageTitle)
      PurchaserAddress.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user is navigated to the Property Address page to 'Review and confirm the address'")
      PurchaserAddress.verifyPageTitle(PurchaserAddress.confirmPageTitle)
      When("the user clicks the Confirm address button")
      PurchaserAddress.clickContinueButton()
      Then("the user is navigated to the Do you want to add phone number? page")
      DoesPurchaserHavePhoneNumber.verifyPageTitle(DoesPurchaserHavePhoneNumber.pageTitle)
      When("the user selects the 'Yes' radio button")
      DoesPurchaserHavePhoneNumber.radioButton(DoesPurchaserHavePhoneNumber.yes)
      And("clicks the Save and continue button")
      DoesPurchaserHavePhoneNumber.saveAndContinue()
      Then("the user is navigated to the What is purchaser's phone number page")
      EnterPurchaserPhoneNumber.verifyPageTitle(EnterPurchaserPhoneNumber.pageTitle)
      When("the user enters their phone number")
      EnterPurchaserPhoneNumber.input(
        By.id(EnterPurchaserPhoneNumber.phoneNumberInputField),
        EnterPurchaserPhoneNumber.phoneNumberValue
      )
      And("clicks the Save and continue button")
      EnterPurchaserPhoneNumber.saveAndContinue()
      Then("the user is navigated to the Does the Purchaser have a National Insurance number? page")
      AddPurchaserNationalInsuranceNumber.verifyPageTitle(AddPurchaserNationalInsuranceNumber.pageTitle)
      When("the user selects the 'No' radio button")
      AddPurchaserNationalInsuranceNumber.radioButton(AddPurchaserNationalInsuranceNumber.no)
      And("clicks the Save and continue button")
      AddPurchaserNationalInsuranceNumber.saveAndContinue()
      Then("the user is navigated to the Provide a form of ID for purchaser page")
      FormOfIDIndividual.verifyPageTitle(FormOfIDIndividual.pageTitle)
      When("the user inputs their ID number or reference")
      FormOfIDIndividual.input(
        By.id(FormOfIDIndividual.purchaserIdNumberOrReference),
        FormOfIDIndividual.idNumberOrReferenceInput
      )
      And("their Country of issue")
      FormOfIDIndividual.input(
        By.id(FormOfIDIndividual.purchaserCountryIssued),
        FormOfIDIndividual.countryIssuedInput
      )
      And("clicks the Save and Continue button")
      FormOfIDIndividual.saveAndContinue()
      Then("the user is navigated to is the Purchaser Acting as a Trustee page")
      ActingAsATrustee.verifyPageTitle(ActingAsATrustee.pageTitle)
      When("the user selects the 'No' radio button")
      ActingAsATrustee.radioButton(ActingAsATrustee.no)
      And("clicks the Save and continue button")
      ActingAsATrustee.saveAndContinue()
      Then("the user is navigated to the Are the Purchaser and Vendor Connected page")
      PurchaserAndVendorConnected.verifyPageTitle(PurchaserAndVendorConnected.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserAndVendorConnected.radioButton(PurchaserAndVendorConnected.yes)
      And("clicks the Save and continue button")
      PurchaserAndVendorConnected.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the 'Change' link for purchaser address")
      PurchaserCheckYourAnswers.clickPurchaserAddressChange()
      Then("the user is navigated to the Address lookup page")
      PurchaserAddress.verifyPageTitle(PurchaserAddress.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAddress.clickAddressManually()
      And("enters their updated address manually")
      PurchaserAddress.verifyPageTitle(PurchaserAddress.editPageTitle)
      PurchaserAddress.enterAddressManually("123", "TEST", "ZZ11 1ZZ")
      Then("the user is navigated to the Property Address page to 'Review and confirm the address'")
      PurchaserAddress.verifyPageTitle(PurchaserAddress.confirmPageTitle)
      When("the user clicks the Confirm address button")
      PurchaserAddress.clickContinueButton()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the 'Change' link for purchasers phone number")
      PurchaserCheckYourAnswers.clickPurchaserPhoneNumberChange()
      Then("the user is navigated to the What is purchaser's phone number page")
      EnterPurchaserPhoneNumber.verifyPageTitle(EnterPurchaserPhoneNumber.pageTitle)
      When("the user updates their phone number")
      EnterPurchaserPhoneNumber.input(
        By.id(EnterPurchaserPhoneNumber.phoneNumberInputField),
        EnterPurchaserPhoneNumber.phoneNumberValue2
      )
      And("clicks the Save and continue button")
      EnterPurchaserPhoneNumber.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the 'Change' link for Purchaser form of id individual")
      PurchaserCheckYourAnswers.clickIndivFormOfIDChange()
      Then("the user is navigated to the Provide a Form of ID for purchaser page")
      FormOfIDIndividual.verifyPageTitle(FormOfIDIndividual.pageTitle)
      When("the user updates their purchaser ID details")
      FormOfIDIndividual.input(
        By.id(FormOfIDIndividual.purchaserIdNumberOrReference),
        FormOfIDIndividual.idNumberOrReferenceInput2
      )
      And("clicks the Save and continue button")
      FormOfIDIndividual.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks the Save and continue button")
      PurchaserCheckYourAnswers.saveAndContinue()
      Then("the user is navigated to the Purchaser Overview page")
      PurchaserOverview.verifyPageTitle(PurchaserOverview.pageTitle)
    }

    Scenario(
      "Complete the Full Purchaser Questions journey as an Individual",
      PurchaserJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("full-purchaser"))
      When("the user clicks on the 'Purchaser Questions' link")
      ReturnTaskList.clickLinkById("task-list-link-purchaser-questions")
      Then("the user is navigated to the Purchaser Overview page")
      PurchaserOverview.verifyPageTitle(PurchaserOverview.pageTitle)
      When("the user clicks the 'Remove' link to remove a purchaser")
      PurchaserOverview.clickRemovePurchaser()
      Then("the user is navigated to Are you sure you want to remove the purchaser page")
      PurchaserRemove.verifyPageTitle(PurchaserRemove.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserRemove.radioButton(PurchaserRemove.yes)
      And("clicks the Save and continue button")
      PurchaserOverview.saveAndContinue()
      Then("the user is navigated to the Purchaser Overview Page")
      PurchaserOverview.verifyPageTitle(PurchaserOverview.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserOverview.radioButton(PurchaserOverview.yes)
      And("clicks the Continue button")
      PurchaserOverview.saveAndContinue()
      Then("the user is navigated to the Purchaser Before you start page")
      PurchaserBeforeYouStart.verifyPageTitle(PurchaserBeforeYouStart.pageTitle)
      When("the user clicks the Continue button")
      PurchaserBeforeYouStart.saveAndContinue()
      Then("the user is navigated to the Who is making the purchase page")
      PurchaserWhoIsMakingThePurchase.verifyPageTitle(PurchaserWhoIsMakingThePurchase.pageTitle)
      When("the user selects the 'An Individual' radio button")
      PurchaserWhoIsMakingThePurchase.radioButton(PurchaserWhoIsMakingThePurchase.individual)
      And("clicks the Save and continue button")
      PurchaserWhoIsMakingThePurchase.saveAndContinue()
      Then("the user is navigated to the What is the purchaser’s full name? page")
      PurchaserName.verifyPageTitle(PurchaserName.pageTitle)
      When("the user inputs their first name, middle name, and surname")
      PurchaserName.input(
        By.id(PurchaserName.forenameId),
        PurchaserName.forenameInput
      )
      PurchaserName.input(
        By.id(PurchaserName.middlenameId),
        PurchaserName.middlenameInput
      )
      PurchaserName.input(
        By.id(PurchaserName.surnameId),
        PurchaserName.surnameInput
      )
      And("clicks the Save and continue button")
      PurchaserName.saveAndContinue()
      Then("the user is navigated to the Address lookup page")
      PurchaserAddress.verifyPageTitle(PurchaserAddress.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAddress.clickAddressManually()
      And("enters their address manually")
      PurchaserAddress.verifyPageTitle(PurchaserAddress.editPageTitle)
      PurchaserAddress.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user should be navigated to the Property Address page to 'Review and confirm the address'")
      PurchaserAddress.verifyPageTitle(PurchaserAddress.confirmPageTitle)
      When("the user clicks the Confirm address button")
      PurchaserAddress.clickContinueButton()
      Then("the user is navigated to is the Purchaser Acting as a Trustee? page")
      ActingAsATrustee.verifyPageTitle(ActingAsATrustee.pageTitle)
      When("the user selects the 'Yes' radio button")
      ActingAsATrustee.radioButton(ActingAsATrustee.yes)
      And("clicks the Save and continue button")
      ActingAsATrustee.saveAndContinue()
      Then("the user is navigated to the Are the purchaser and vendor connected? page")
      PurchaserAndVendorConnected.verifyPageTitle(PurchaserAndVendorConnected.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserAndVendorConnected.radioButton(PurchaserAndVendorConnected.yes)
      And("clicks the Save and continue button")
      PurchaserAndVendorConnected.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      And("clicks the Save and continue button")
      PurchaserCheckYourAnswers.saveAndContinue()
      Then("the user is navigated to the Purchaser Overview page")
      PurchaserOverview.verifyPageTitle(PurchaserOverview.pageTitle)
      When("the user clicks the 'Change' link for Purchaser")
      PurchaserOverview.clickPurchaserChange()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
    }

    Scenario(
      "Complete the Purchaser Questions journey as a Company and changing all forms of id",
      PurchaserJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("no-purchaser"))
      When("the user clicks on the 'Purchaser Questions' link")
      ReturnTaskList.clickLinkById("task-list-link-purchaser-questions")
      Then("the user is navigated to the Before you start page")
      PurchaserBeforeYouStart.verifyPageTitle(PurchaserBeforeYouStart.pageTitle)
      When("the user clicks the Continue button")
      PurchaserBeforeYouStart.saveAndContinue()
      Then("the user is navigated to the Who is making the purchase page")
      PurchaserWhoIsMakingThePurchase.verifyPageTitle(PurchaserWhoIsMakingThePurchase.pageTitle)
      When("the user selects the 'A Company' radio button")
      PurchaserWhoIsMakingThePurchase.radioButton(PurchaserWhoIsMakingThePurchase.company)
      And("clicks the Save and continue button")
      PurchaserWhoIsMakingThePurchase.saveAndContinue()
      Then("the user is navigated to the What is the company's name? page")
      PurchaserName.verifyPageTitle(PurchaserName.pageTitle)
      When("the user inputs their company name")
      PurchaserName.input(
        By.id(PurchaserName.companyId),
        PurchaserName.companyName2
      )
      And("clicks the Save and continue button")
      PurchaserName.saveAndContinue()
      Then("the user is navigated to the Address lookup page")
      PurchaserAddress.verifyPageTitle(PurchaserAddress.pageTitleCompany)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAddress.clickAddressManually()
      And("enters their address manually")
      PurchaserAddress.verifyPageTitle(PurchaserAddress.editPageTitleCompany)
      PurchaserAddress.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user is navigated to the Property Address page to 'Review and confirm the address'")
      PurchaserAddress.verifyPageTitle(PurchaserAddress.confirmPageTitleCompany)
      When("the user clicks the Confirm address button")
      PurchaserAddress.clickContinueButton()
      Then("the user is navigated to the Do you want to add a phone number? page")
      DoesPurchaserHavePhoneNumber.verifyPageTitle(DoesPurchaserHavePhoneNumber.pageTitle)
      When("the user selects the 'No' radio button")
      DoesPurchaserHavePhoneNumber.radioButton(DoesPurchaserHavePhoneNumber.no)
      And("clicks the Save and continue button")
      DoesPurchaserHavePhoneNumber.saveAndContinue()
      Then("the user is navigated to the Confirm Purchaser Identity page")
      ConfirmPurchaserIdentity.verifyPageTitle(ConfirmPurchaserIdentity.pageTitle)
      When("the user selects the 'VAT registration number' radio button")
      ConfirmPurchaserIdentity.radioButton(ConfirmPurchaserIdentity.vatRegistrationNumber)
      And("clicks the Save and continue button")
      ConfirmPurchaserIdentity.saveAndContinue()
      Then("the user is navigated to the What is the purchaser's VAT Registration number page")
      VATRegistrationNumber.verifyPageTitle(VATRegistrationNumber.pageTitle)
      When("the user enters their VAT registration number")
      VATRegistrationNumber.input(
        By.id(VATRegistrationNumber.vat),
        VATRegistrationNumber.VATNumber
      )
      And("clicks the Save and continue button")
      VATRegistrationNumber.saveAndContinue()
      Then("the user is navigated to the Do you know type of company? page")
      DoYouKnowTypeOfCompany.verifyPageTitle(DoYouKnowTypeOfCompany.pageTitle)
      And("the user selects the 'No' radio button")
      DoYouKnowTypeOfCompany.radioButton(DoYouKnowTypeOfCompany.no)
      And("clicks the Save and continue button")
      TypeOfCompany.saveAndContinue()
      Then("the user is navigated to is the Purchaser Acting as a Trustee? page")
      ActingAsATrustee.verifyPageTitle(ActingAsATrustee.pageTitle)
      When("the user selects the 'Yes' radio button")
      ActingAsATrustee.radioButton(ActingAsATrustee.yes)
      And("clicks the Save and continue button")
      ActingAsATrustee.saveAndContinue()
      Then("the user is navigated to the Are the purchaser and vendor connected? page")
      PurchaserAndVendorConnected.verifyPageTitle(PurchaserAndVendorConnected.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserAndVendorConnected.radioButton(PurchaserAndVendorConnected.yes)
      And("clicks the Save and continue button")
      PurchaserAndVendorConnected.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the 'Change' link for entering VAT registration number")
      PurchaserCheckYourAnswers.clickVATRegistrationNumberChange()
      Then("the user is navigated to the What is the purchaser's VAT Registration number page")
      VATRegistrationNumber.verifyPageTitle(VATRegistrationNumber.pageTitle)
      When("the user updates their VAT registration number")
      VATRegistrationNumber.input(
        By.id(VATRegistrationNumber.vat),
        VATRegistrationNumber.VATNumber2
      )
      And("clicks the Save and continue button")
      VATRegistrationNumber.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the 'Change' link for Form of ID")
      PurchaserCheckYourAnswers.clickFormOfIDChange()
      Then("the user is navigated to the Confirm Purchaser Identity page")
      ConfirmPurchaserIdentity.verifyPageTitle(ConfirmPurchaserIdentity.pageTitle)
      When("the user updates to 'Partnership Unique Taxpayer Reference (UTR)' radio button")
      ConfirmPurchaserIdentity.radioButton(ConfirmPurchaserIdentity.partnershipUTR)
      And("clicks the Save and continue button")
      ConfirmPurchaserIdentity.saveAndContinue()
      Then("the user is navigated to the enter Purchaser's Partnership UTR page")
      PartnershipUTR.verifyPageTitle(PartnershipUTR.pageTitle)
      When("the user inputs their company's Partnership Unique Taxpayer Reference (UTR)")
      PartnershipUTR.input(
        By.id(PartnershipUTR.purchaserUTRReference),
        PartnershipUTR.purchaserUTRInput
      )
      And("clicks the Save and continue button")
      PartnershipUTR.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the 'Change' link for Form of ID")
      PurchaserCheckYourAnswers.clickFormOfIDChange()
      Then("the user is navigated to the Confirm Purchaser Identity page")
      ConfirmPurchaserIdentity.verifyPageTitle(ConfirmPurchaserIdentity.pageTitle)
      When("the user selects the 'Corporation tax Unique Taxpayer Reference (UTR)' radio button")
      ConfirmPurchaserIdentity.radioButton(ConfirmPurchaserIdentity.corporationTaxUTR)
      And("clicks the Save and continue button")
      ConfirmPurchaserIdentity.saveAndContinue()
      Then("the user is navigated to the enter Purchaser's Corporation Tax UTR page")
      CorporationTaxUTR.verifyPageTitle(CorporationTaxUTR.pageTitle)
      When("the user inputs their company's Corporation tax Unique Taxpayer Reference (UTR)")
      CorporationTaxUTR.input(
        By.id(CorporationTaxUTR.corporationTaxUTR),
        CorporationTaxUTR.corporationTaxUTRInput
      )
      And("clicks the Save and continue button")
      CorporationTaxUTR.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the 'Change' link for Form of ID")
      PurchaserCheckYourAnswers.clickFormOfIDChange()
      Then("the user is navigated to the Confirm Purchaser Identity page")
      ConfirmPurchaserIdentity.verifyPageTitle(ConfirmPurchaserIdentity.pageTitle)
      When("the user selects the 'Another form of ID' radio button")
      ConfirmPurchaserIdentity.radioButton(ConfirmPurchaserIdentity.anotherFormOfID)
      And("clicks the Save and continue button")
      ConfirmPurchaserIdentity.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the 'Change' link for Form of ID")
      PurchaserCheckYourAnswers.clickIdAndCountryIssuedChange()
      Then("the user is navigated to the Provide a Form of ID Company page")
      FormOfIDCompany.verifyPageTitle(FormOfIDCompany.pageTitle)
      When("the user inputs their ID number or reference")
      FormOfIDCompany.input(
        By.id(FormOfIDCompany.idNumberOrReference),
        FormOfIDCompany.idNumberOrReferenceInput
      )
      And("their Country of issue")
      FormOfIDCompany.input(
        By.id(FormOfIDCompany.countryOfIssue),
        FormOfIDCompany.countryOfIssueInput
      )
      And("clicks the Save and continue button")
      FormOfIDCompany.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the 'Change' link for Do you want to add phone number")
      PurchaserCheckYourAnswers.clickAddPhoneNumberChange()
      Then("the user is navigated to the Do you want to add phone number? page")
      DoesPurchaserHavePhoneNumber.verifyPageTitle(DoesPurchaserHavePhoneNumber.pageTitle)
      When("the user selects the 'Yes' radio button")
      DoesPurchaserHavePhoneNumber.radioButton(DoesPurchaserHavePhoneNumber.yes)
      And("clicks the Save and continue button")
      DoesPurchaserHavePhoneNumber.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the link for entering a phone number")
      PurchaserCheckYourAnswers.clickPurchaserPhoneNumberChange()
      Then("the user is navigated to the What is purchaser's phone number page")
      EnterPurchaserPhoneNumber.verifyPageTitle(EnterPurchaserPhoneNumber.pageTitle)
      When("the user enters their phone number")
      EnterPurchaserPhoneNumber.input(
        By.id(EnterPurchaserPhoneNumber.phoneNumberInputField),
        EnterPurchaserPhoneNumber.phoneNumberValue
      )
      And("clicks the Save and continue button")
      EnterPurchaserPhoneNumber.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the 'Change' link for do you know company name")
      PurchaserCheckYourAnswers.clickDoYouKnowCompanyNameChange()
      Then("the user is navigated to the Do you know the company name? page")
      DoYouKnowTypeOfCompany.verifyPageTitle(DoYouKnowTypeOfCompany.pageTitle)
      When("the user selects the 'Yes' radio button")
      DoYouKnowTypeOfCompany.radioButton(DoYouKnowTypeOfCompany.yes)
      And("clicks the Save and continue button")
      DoYouKnowTypeOfCompany.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the 'Change' link for Type of company")
      PurchaserCheckYourAnswers.clickTypeOfCompanyChange()
      Then("the user is navigated to the What type of company? page")
      TypeOfCompany.verifyPageTitle(TypeOfCompany.pageTitle)
      When("the user updates their types of company")
      TypeOfCompany.checkbox(
        TypeOfCompany.Unincorporated_sole_trader_other_than_builder,
        true
      )
      TypeOfCompany.checkbox(TypeOfCompany.Superannuation_or_pension_fund, true)
      And("clicks the Save and continue button")
      TypeOfCompany.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the 'Change' link for Acting as a Trustee")
      PurchaserCheckYourAnswers.clickIsCompanyActingAsTrusteeChange()
      Then("the user is navigated to is the Purchaser Acting as a Trustee? page")
      ActingAsATrustee.verifyPageTitle(ActingAsATrustee.pageTitle)
      When("the user selects the 'No' radio button")
      ActingAsATrustee.radioButton(ActingAsATrustee.no)
      And("clicks the Save and continue button")
      ActingAsATrustee.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the 'Change' link for Are the purchaser and vendor connected")
      PurchaserCheckYourAnswers.clickAreCompanyAndVendorConnectedChange()
      Then("User should be navigated to Are purchaser and vendor connected page")
      PurchaserAndVendorConnected.verifyPageTitle(PurchaserAndVendorConnected.pageTitle)
      When("the user selects the 'No' radio button")
      PurchaserAndVendorConnected.radioButton(PurchaserAndVendorConnected.no)
      And("clicks the Save and continue button")
      PurchaserAndVendorConnected.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks the Save and continue button")
      PurchaserCheckYourAnswers.saveAndContinue()
      Then("the user is navigated to the Purchaser Overview page")
      PurchaserOverview.verifyPageTitle(PurchaserOverview.pageTitle)
      When("the user clicks the Add a purchaser button")
      PurchaserOverview.saveAndContinue()
      Then("the user is navigated to the Before you start page")
      PurchaserBeforeYouStart.verifyPageTitle(PurchaserBeforeYouStart.pageTitle)
    }

    Scenario(
      "Complete the Purchaser Questions journey as a Company and changed to an Individual",
      PurchaserJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("incomplete-purchaser"))
      When("the user clicks on the 'Purchaser Questions' link")
      ReturnTaskList.clickLinkById("task-list-link-purchaser-questions")
      Then("the user is navigated to the Before you start page")
      PurchaserBeforeYouStart.verifyPageTitle(PurchaserBeforeYouStart.pageTitle)
      When("the user clicks the Continue button")
      PurchaserBeforeYouStart.saveAndContinue()
      Then("the user is navigated to the Confirm purchaser name page")
      ConfirmPurchaserName.verifyPageTitle(ConfirmPurchaserName.pageTitle)
      When("the user selects the 'Yes' radio button")
      ConfirmPurchaserName.radioButton(ConfirmPurchaserName.yes)
      And("clicks the Continue button")
      ConfirmPurchaserName.saveAndContinue()
      Then("the user is navigated to the Address lookup page")
      PurchaserAddress.verifyPageTitle(PurchaserAddress.pageTitleCompanyStub)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAddress.clickAddressManually()
      And("enters their address manually")
      PurchaserAddress.verifyPageTitle(PurchaserAddress.editPageTitleCompanyStub)
      PurchaserAddress.enterAddressManually("123", "ABC", "TE13 1ES")
      Then("the user is navigated to the Property Address page to 'Review and confirm the address'")
      PurchaserAddress.verifyPageTitle(PurchaserAddress.confirmPageTitleCompanyStub)
      When("the user clicks the Confirm address button")
      PurchaserAddress.clickContinueButton()
      Then("the user is navigated to the Do you want to add phone number? page")
      DoesPurchaserHavePhoneNumber.verifyPageTitle(DoesPurchaserHavePhoneNumber.pageTitle)
      When("the user selects the 'No' radio button")
      DoesPurchaserHavePhoneNumber.radioButton(DoesPurchaserHavePhoneNumber.no)
      And("clicks the Save and continue button")
      DoesPurchaserHavePhoneNumber.saveAndContinue()
      Then("the user is navigated to the Confirm Purchaser Identity page")
      ConfirmPurchaserIdentity.verifyPageTitle(ConfirmPurchaserIdentity.pageTitleStub)
      When("the user selects the 'VAT registration number' radio button")
      ConfirmPurchaserIdentity.radioButton(ConfirmPurchaserIdentity.vatRegistrationNumber)
      And("clicks the Save and continue button")
      ConfirmPurchaserIdentity.saveAndContinue()
      Then("the user is navigated to the What is the purchaser's VAT Registration number page")
      VATRegistrationNumber.verifyPageTitle(VATRegistrationNumber.pageTitle)
      When("the user enters their VAT registration number")
      VATRegistrationNumber.input(
        By.id(VATRegistrationNumber.vat),
        VATRegistrationNumber.VATNumber
      )
      And("clicks the Save and continue button")
      VATRegistrationNumber.saveAndContinue()
      Then("the user is navigated to the Do you know type of company? page")
      DoYouKnowTypeOfCompany.verifyPageTitle(DoYouKnowTypeOfCompany.pageTitle)
      And("the user selects the 'yes' radio button")
      DoYouKnowTypeOfCompany.radioButton(DoYouKnowTypeOfCompany.yes)
      And("clicks the Save and continue button")
      DoYouKnowTypeOfCompany.saveAndContinue()
      Then("the user is navigated to the What type of company? page")
      TypeOfCompany.verifyPageTitle(TypeOfCompany.pageTitle)
      When("the user selects 2 types of company")
      TypeOfCompany.checkbox(TypeOfCompany.Bank, true)
      TypeOfCompany.checkbox(TypeOfCompany.Building_Society, true)
      And("clicks the Save and continue button")
      TypeOfCompany.saveAndContinue()
      Then("the user is navigated to is the Purchaser Acting as a Trustee? page")
      ActingAsATrustee.verifyPageTitle(ActingAsATrustee.pageTitle)
      When("the user selects the 'Yes' radio button")
      ActingAsATrustee.radioButton(ActingAsATrustee.yes)
      And("clicks the Save and continue button")
      ActingAsATrustee.saveAndContinue()
      Then("the user is navigated to the Are the purchaser and vendor connected? page")
      PurchaserAndVendorConnected.verifyPageTitle(PurchaserAndVendorConnected.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserAndVendorConnected.radioButton(PurchaserAndVendorConnected.yes)
      And("clicks the Save and continue button")
      PurchaserAndVendorConnected.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the 'Change' link for purchaser type")
      PurchaserCheckYourAnswers.clickPurchaserTypeChange()
      Then("the user is navigated to the Who is making the purchase page")
      PurchaserWhoIsMakingThePurchase.verifyPageTitle(PurchaserWhoIsMakingThePurchase.pageTitle)
      When("the user selects the 'An Individual' radio button")
      PurchaserWhoIsMakingThePurchase.radioButton(PurchaserWhoIsMakingThePurchase.individual)
      And("clicks the Save and continue button")
      PurchaserWhoIsMakingThePurchase.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the link for entering the purchaser's full name")
      PurchaserCheckYourAnswers.clickPurchaserNameChange()
      Then("the user is navigated to the What is the purchaser’s full name? page")
      PurchaserName.verifyPageTitle(PurchaserName.pageTitle)
      When("the user inputs their first name, middle name, and surname")
      PurchaserName.input(
        By.id(PurchaserName.forenameId),
        PurchaserName.forenameInput
      )
      PurchaserName.input(
        By.id(PurchaserName.middlenameId),
        PurchaserName.middlenameInput
      )
      PurchaserName.input(
        By.id(PurchaserName.surnameId),
        PurchaserName.surnameInput
      )
      PurchaserName.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the 'Change' link for Does the Purchaser have a National Insurance number?")
      PurchaserCheckYourAnswers.clickDoPurchaserHaveNINumberChange()
      Then("the user is navigated to the Does the Purchaser have a National Insurance number? page")
      AddPurchaserNationalInsuranceNumber.verifyPageTitle(AddPurchaserNationalInsuranceNumber.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddPurchaserNationalInsuranceNumber.radioButton(AddPurchaserNationalInsuranceNumber.yes)
      And("clicks the Save and continue button")
      AddPurchaserNationalInsuranceNumber.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the link for entering National Insurance number")
      PurchaserCheckYourAnswers.clickPurchaserNINumberChange()
      Then("the user is navigated to the What is the Purchaser's national insurance number? page")
      EnterPurchaserNationalInsuranceNumber.verifyPageTitle(EnterPurchaserNationalInsuranceNumber.pageTitle)
      When("the user inputs a National Insurance Number")
      EnterPurchaserNationalInsuranceNumber.input(
        By.id(EnterPurchaserNationalInsuranceNumber.Nino),
        EnterPurchaserNationalInsuranceNumber.NinoValue2
      )
      And("clicks the Save and continue button")
      EnterPurchaserNationalInsuranceNumber.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      When("the user clicks on the link for entering Date of Birth")
      PurchaserCheckYourAnswers.clickPurchaserDOBChange()
      Then("User should be navigated to purchaser date of birth page")
      DateOfBirth.verifyPageTitle(DateOfBirth.pageTitle)
      When("the user inputs their Date of Birth")
      DateOfBirth.enterDateOfBirth()
      And("clicks the Save and continue button")
      DateOfBirth.saveAndContinue()
      Then("the user is navigated to the Check your answers page")
      PurchaserCheckYourAnswers.verifyPageTitle(PurchaserCheckYourAnswers.pageTitle)
      And("clicks the Save and continue button")
      PurchaserCheckYourAnswers.saveAndContinue()
    }

    Scenario(
      "Begin the Purchaser Journey with two purchasers and change the lead purchaser",
      PurchaserJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("2-purchasers-company"))
      When("the user clicks on the 'Purchaser Questions' link")
      WhoIsTheVendor.clickLinkById("task-list-link-purchaser-questions")
      Then("the user is navigated to the Purchaser Overview Page")
      PurchaserOverview.verifyPageTitle(PurchaserOverview.pageTitle)
      And("The user clicks the change main purchaser link")
      PurchaserOverview.clickMainPurchaserChange()
      Then("The user is navigated to the Change Purchaser page")
      ChangePurchaser.verifyPageTitle(ChangePurchaser.pageTitle)
      And("User clicks which purchaser to change")
      ChangePurchaser.radioButton(ChangePurchaser.secondPurchaser)
      And("clicks save and continue")
      ChangePurchaser.saveAndContinue()
    }
  }
}
