/*
 * Copyright 2026 HM Revenue & Customs
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

import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen}
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.verbs.ShouldVerb
import uk.gov.hmrc.selenium.webdriver.{Browser, ScreenshotOnFailure}
import uk.gov.hmrc.ui.pages.*
import uk.gov.hmrc.ui.pages.TaxCalculations.TaxCalculationsBeforeYouStart
import uk.gov.hmrc.ui.tags.*
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation

class TaxCalculationsQuestionsSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing Frontend Tax Calculations") {

    Scenario(
      "Complete the Freehold calculated Tax Calculation Journey",
      TaxCalculationJourney
    ) {

      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        returnId = Some("freehold-tax-calculated")
      )

      When("the user starts the tax calculations journey")
      ReturnTaskList.clickLinkById("task-list-link-tax-calculation-questions")
      Then("the Freehold calculated Before you start page is displayed")
      TaxCalculationsBeforeYouStart.verifyPageTitle(TaxCalculationsBeforeYouStart.pageTitle)
    }

    Scenario(
      "Complete the Freehold not calculated Tax Calculation Journey",
      TaxCalculationJourney
    ) {

      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        returnId = Some("freehold-self-assessed")
      )

      When("the user starts the tax calculations journey")
      ReturnTaskList.clickLinkById("task-list-link-tax-calculation-questions")
      Then("the Freehold calculated Before you start page is displayed")
      TaxCalculationsBeforeYouStart.verifyPageTitle(TaxCalculationsBeforeYouStart.pageTitleFreeholdNotCalculated)
    }

    Scenario(
      "Complete the Leasehold calculated Tax Calculation Journey",
      TaxCalculationJourney
    ) {

      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        returnId = Some("leasehold-tax-calculated")
      )

      When("the user starts the tax calculations journey")
      ReturnTaskList.clickLinkById("task-list-link-tax-calculation-questions")
      Then("the Freehold calculated Before you start page is displayed")
      TaxCalculationsBeforeYouStart.verifyPageTitle(TaxCalculationsBeforeYouStart.pageTitleLeaseholdCalculated)
    }

    Scenario(
      "Complete the Leasehold not calculated Tax Calculation Journey",
      TaxCalculationJourney
    ) {

      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        returnId = Some("leasehold-self-assessed")
      )

      When("the user starts the tax calculations journey")
      ReturnTaskList.clickLinkById("task-list-link-tax-calculation-questions")
      Then("the Freehold calculated Before you start page is displayed")
      TaxCalculationsBeforeYouStart.verifyPageTitle(TaxCalculationsBeforeYouStart.pageTitleLeaseholdNotCalculated)
    }
  }
}
