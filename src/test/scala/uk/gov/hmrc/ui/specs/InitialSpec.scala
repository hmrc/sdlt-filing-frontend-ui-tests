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
import uk.gov.hmrc.ui.pages.{AuthWizard, IndividualOrCompanyPage, InitialPage, IsAnIndividualPage}
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

  Feature("SDLT Filing frontend preliminary questions- who is making purchase screen") {
    Scenario("Hit the TaskList with no return id ") {
      Given("I enter login using the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation)
      Then("user navigates to Individual or company page")
      IndividualOrCompanyPage.navigateToPage(IndividualOrCompanyPage.pageUrl)
      And("user should be on Individual or company page")
      IndividualOrCompanyPage.verifyPageTitle(IndividualOrCompanyPage.pageTitle)
      When("user clicks An Individual Radio Button")
      IndividualOrCompanyPage.radioButton(IndividualOrCompanyPage.individual)
      And("user clicks An Save and Continue Button")
      IndividualOrCompanyPage.saveAndContinue()
     /* Then("user is on purchase name screen")
      PurchasePage.verifyPageTitle(purchasePage.pageTitle)*/
    }

    Scenario("Hit the TaskList with a return id") {
      Given("I enter login using the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, Some("123456"))
      Then("user navigates to Individual or company page")
      IndividualOrCompanyPage.navigateToPage(IndividualOrCompanyPage.pageUrl)
      And("user should be on Individual or company page")
      IndividualOrCompanyPage.verifyPageTitle(IndividualOrCompanyPage.pageTitle)
      When("user clicks A Business Radio Button")
      IndividualOrCompanyPage.radioButton(IndividualOrCompanyPage.business)
      And("user clicks An Save and Continue Button")
      IndividualOrCompanyPage.saveAndContinue()
     /* Then("user is on purchase name screen")
       PurchasePage.verifyPageTitle(purchasePage.pageTitle)*/
    }
  }
}
