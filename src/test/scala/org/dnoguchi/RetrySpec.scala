package org.dnoguchi

import org.scalatest.FunSpec

class RetrySpec extends FunSpec {

  describe("Usage") {
    it("通常呼び出し") {
      val result = Retry.run {
        1 + 2
      }(1)
      assert(result === Some(3))
    }

    it("1秒間隔で3回リトライ") {
      val result = Retry.run {
        "some error occured"(999)
      }(3, 1000)
      assert(result === None)
    }
  }
}

