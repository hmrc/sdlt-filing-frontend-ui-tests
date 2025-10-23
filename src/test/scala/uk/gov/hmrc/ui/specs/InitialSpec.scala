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
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen}
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.verbs.ShouldVerb
import uk.gov.hmrc.selenium.webdriver.{Browser, ScreenshotOnFailure}
import uk.gov.hmrc.ui.pages.{AuthWizard, CYAPage, IndividualOrCompanyPage, InitialPage, IsAnIndividualPage}
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation

class InitialSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing frontend Task List Homepage") {
  /*  Scenario("Update purchaser type from CYA page") {
      Given("I enter login using the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation)
      And("user navigates to check your answers page")
      CYAPage.navigateToPage(CYAPage.pageUrl)
      Then("user should be on check your answers page")
      CYAPage.verifyPageTitle(CYAPage.pageTitle)
      When("user clicks on change for purchaser type")
      CYAPage.clickLinkById(CYAPage.changePurchaserType)
      Then("user should be on individual or company page")
      IndividualOrCompanyPage.verifyPageTitle(IndividualOrCompanyPage.pageTitle)
    }
    Scenario("Update purchaser's name form CYA page") {
      Given("I enter login using the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation)
      And("user navigates to check your answers page")
      CYAPage.navigateToPage(CYAPage.pageUrl)
      Then("user should be on check your answers page")
      CYAPage.verifyPageTitle(CYAPage.pageTitle)
      When("user clicks on change for purchaser name")
      CYAPage.clickLinkById(CYAPage.changePurchaserName)
      Then("user should be on purchaser's name page")
      IsAnIndividualPage.verifyPageTitle(IsAnIndividualPage.pageTitle)
    }
    Scenario("Update property address form CYA page") {
      Given("I enter login using the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation)
      And("user navigates to check your answers page")
      CYAPage.navigateToPage(CYAPage.pageUrl)
      Then("user should be on check your answers page")
      CYAPage.verifyPageTitle(CYAPage.pageTitle)
      When("user clicks on change for property address")
      CYAPage.clickLinkById(CYAPage.changePropertyAddress)
      Then("user should be on property address page")
      PropertyAddressPage.verifyPageTitle(PropertyAddressPage.pageTitle)
    }
    Scenario("Update transaction type  form CYA page") {
      Given("I enter login using the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation)
      And("user navigates to check your answers page")
      CYAPage.navigateToPage(CYAPage.pageUrl)
      Then("user should be on check your answers page")
      CYAPage.verifyPageTitle(CYAPage.pageTitle)
      When("user clicks on change for transaction type")
      CYAPage.clickLinkById(CYAPage.changeTransactionType)
      Then("user should be on transaction type page")
      TransactionPage.verifyPageTitle(TransactionPage.pageTitle)*/
    }
  }
}